package JavaRacer;

public class Camera {
    int worldX, worldY;
    int screenMidX, screenMidY;
    KeyHandler keyHandle;
    GameWindow gameWindow;
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
            this.worldX -=8;
        }
        else if(keyHandle.dPressed){
            this.worldX += 8;
        }   
        else if(keyHandle.wPressed){
            this.worldY -=8;
        }
        else if(keyHandle.sPressed){
            this.worldY += 8;
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
