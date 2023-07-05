package JavaRacer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Agent {
    int agentX,agentY; //x,y coordinates in the world and rotation of the car in degrees. 0 representing right as in cartesian system(stored in velocity)
    int screenX, screenY; //the x,y coordinates of the car on screen
    Rectangle solidArea = new Rectangle(); // aka hitbox of the car
    public double frictionCoefficient; //road friction: 1 in asphalt, 0.1 in grass checked by CollisionControl class
    public int laps = 0;
    public double points = 0;
    public boolean onFinishLine, offFinishLine, isCollided = false; //flags for game logic
    public ArrayList<Integer> instructions;
    int nextActionTimer,instructionIndex;
    public boolean isFinished = false;
    public BufferedImage playerModel;
    GameWindow gameWindow;
    Velocity velocity;

    public Agent(GameWindow gw){
        this.gameWindow = gw;
        setDefault();
    }
    public void setDefault(){
        this.agentX=MapLoader.spawnX*gameWindow.tileSize;
        this.agentY=MapLoader.spawnY*gameWindow.tileSize;
        this.velocity = new Velocity();   
        this.instructions = new ArrayList<Integer>();    
        switch (MapLoader.spawnDirection) {
            case 0:
                velocity.angle = 0;
                break;
            case 1:
                velocity.angle = 90;
                break;
            case 2:
                velocity.angle = 180;
                break;
            case 3:
                velocity.angle = 270;
                break;
            default:
                break;
        }
        this.solidArea.x = 30;
        this.solidArea.y = 5;
        this.solidArea.width = 25;
        this.solidArea.height = 25;
        try {
            File tmp = new File("source/car.png");
            playerModel = ImageIO.read(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        frictionCoefficient = gameWindow.collisionControl.checkCollision(this);
        checkLaps();
        calculatePoints();
        if(nextActionTimer%5 == 0 && instructionIndex<instructions.size()){ //take action every 5 frames
            int rotationAngle;
            double netVelocity = velocity.netVelocity();
            switch (instructions.get(instructionIndex)) {
                case 0: //accelerate
                    velocity.accelerate(0.4*frictionCoefficient);
                    break;
                case 1: //deaccelerate
                    velocity.accelerate(-0.5);
                    break;
                case 2: //turn left
                    rotationAngle = 10;
                    if (netVelocity > 0) {
                        double scale = 1.0 / (1.0 + netVelocity * 0.1); 
                        rotationAngle *= scale;
                        rotationAngle = Math.max(rotationAngle, 2);
                    }
                    velocity.rotate(rotationAngle);
                    break;
                case 3: //turn right
                    rotationAngle = -10;
                    if (netVelocity > 0) {
                        double scale = 1.0 / (1.0 + netVelocity * 0.1); 
                        rotationAngle *= scale;
                        rotationAngle = Math.min(rotationAngle, -2); 
                    }
                    velocity.rotate(rotationAngle);
                    break;
                case 4: //Do nothing (keep velocity and angle)
                    break;
            }
            instructionIndex++;
        }
        
        while(frictionCoefficient<1&&velocity.netVelocity()>10*frictionCoefficient){
            velocity.accelerate(-0.5);
        }
        if(instructionIndex == instructions.size()-1){
            this.isFinished = true;
        }
        this.agentX += velocity.X;
        this.agentY -= velocity.Y;
        nextActionTimer++;
    }
    public void checkLaps(){
        if(onFinishLine){
            if(offFinishLine&&points>(laps*2500)+1000){
                laps++;
                points += 5000;
            }
            offFinishLine = false;
        }
        else{
            offFinishLine = true;
        }
    }
    public void calculatePoints(){
        if(frictionCoefficient<1){
            if(!isCollided){
                points-=1000;
                isCollided = true;
            }
            points -= (velocity.netVelocity()/frictionCoefficient)/10;
        }
        else{
            isCollided = false;
            points += velocity.netVelocity()/10;
        }
        if(points<0){
            points = 0;
        }
    }
    public void draw(Graphics2D graphics){
        int camX = gameWindow.camera.getWorldX();
        int camY =  gameWindow.camera.getWorldY();
        int screenMidX = gameWindow.camera.getScreenMidX();
        int screenMidY = gameWindow.camera.getScreenMidY();
        int tileSize = gameWindow.getTileSize();

        int screenX = agentX - camX + screenMidX;
        int screenY = agentY - camY + screenMidY;

        if(agentX>camX-screenMidX -1*tileSize&&
        agentX<agentX+screenMidX+3*tileSize&&
        agentY>agentY-screenMidY-1*tileSize&&
        agentY<agentY+screenMidY+3*tileSize){
            AffineTransform oldTransform = graphics.getTransform(); // save old graphics orientation
            graphics.rotate(Math.toRadians(-velocity.angle),screenX+playerModel.getWidth()/4,screenY+playerModel.getHeight()/4);
            graphics.drawImage(playerModel,screenX,screenY,playerModel.getWidth()/2,playerModel.getHeight()/2,null);
            graphics.setTransform(oldTransform); // restore old graphics
        }
    }
    public void reset(){
        this.isFinished = false;
        this.instructionIndex = 0;
        this.points = 0;
        this.laps = 0;
        this.agentX=MapLoader.spawnX*gameWindow.tileSize;
        this.agentY=MapLoader.spawnY*gameWindow.tileSize;
        velocity.X = 0;
        velocity.Y = 0;
        switch (MapLoader.spawnDirection) {
            case 0:
                velocity.angle = 0;
                break;
            case 1:
                velocity.angle = 90;
                break;
            case 2:
                velocity.angle = 180;
                break;
            case 3:
                velocity.angle = 270;
                break;
            default:
                break;
        }

    }
    public String toString(){
        return this.agentX + " " + this.agentY;
    }
    public int getAgentX(){
        return this.agentX;
    }
    public int getAgentY(){
        return this.agentY;
    }
    public double getPoints(){
        return this.points;
    }
    public int getLaps(){
        return this.laps;
    }
    public Velocity getVelocity(){
        return this.velocity;
    }
    public boolean getIsCollided(){
        return this.isCollided;
    }
}
