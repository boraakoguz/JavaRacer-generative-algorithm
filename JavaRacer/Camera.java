package JavaRacer;

public class Camera {
    int worldX, worldY;
    int screenMidX, screenMidY;
    KeyHandler keyHandle;
    GameWindow gameWindow;
    int speed = 10;
    public Camera(GameWindow gw, KeyHandler kh, int worldX, int worldY){
        this.gameWindow = gw;
        this.keyHandle = kh;
        this.worldX = worldX;
        this.worldY = worldY;
        this.screenMidX = gw.WIDTH/2;
        this.screenMidY = gw.HEIGHT/2;
    }
    public void update(){
        if (keyHandle.aPressed){
            this.worldX -=speed;
        }
        else if(keyHandle.dPressed){
            this.worldX += speed;
        }   
        else if(keyHandle.wPressed){
            this.worldY -=speed;
        }
        else if(keyHandle.sPressed){
            this.worldY += speed;
        }
    }
    public int getWorldX(){
        return this.worldX;
    }
    public void setWorldX(int worldX){
        this.worldX = worldX;
    }
    public int getWorldY(){
        return this.worldY;
    }
    public void setWorldY(int worldY){
        this.worldY = worldY;
    }
    public int getScreenMidX(){
        return this.screenMidX;
    }
    public int getScreenMidY(){
        return this.screenMidY;
    }
}
