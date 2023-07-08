package JavaRacer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileManager {
    
    GameWindow gameWindow;
    MapTiles[] tiles;
    public int[][] map;
    int row,collumn,tileSize;
    
    public TileManager(GameWindow gw){
        this.gameWindow = gw;
        tiles = new MapTiles[9];
        loadTileImages();
        map = MapLoader.loadMap();
        row = gameWindow.getRow();
        collumn = gameWindow.getCollumn();
        tileSize = gameWindow.getTileSize();
    }
    public void loadTileImages(){
         try {
            tiles[0] = new MapTiles();
            tiles[0].image = ImageIO.read(new File("source/grass0.png"));
            tiles[0].friction = 0.2;
            
            tiles[1] = new MapTiles();
            tiles[1].image = ImageIO.read(new File("source/grass1.png"));
            tiles[1].friction = 0.2;
            
            tiles[2] = new MapTiles();
            tiles[2].image = ImageIO.read(new File("source/asphalt0.png"));
            tiles[2].friction = 1;
            
            tiles[3] = new MapTiles();
            tiles[3].image = ImageIO.read(new File("source/asphalt1.png"));
            tiles[3].friction = 1;
            
            tiles[4] = new MapTiles();
            tiles[4].image = ImageIO.read(new File("source/asphalt2.png"));
            tiles[4].friction = 1;
            
            tiles[5] = new MapTiles();
            tiles[5].image = ImageIO.read(new File("source/boundary.png"));
            tiles[5].friction = 0.5;

            tiles[6] = new MapTiles();
            tiles[6].image = ImageIO.read(new File("source/line.png"));
            tiles[6].friction = 1;
            tiles[6].isFinishLine = true;

            tiles[7] = new MapTiles();
            tiles[7].image = ImageIO.read(new File("source/water0.png"));
            tiles[7].friction = 0.1;

            tiles[8] = new MapTiles();
            tiles[8].image = ImageIO.read(new File("source/ground.png"));
            tiles[8].friction = 0.1;
                  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2d, Camera camera){ //Draws the map around the camera
        int cameraX = camera.getWorldX();
        int cameraY = camera.getWorldY();
        int screenMidX = camera.getScreenMidX();
        int screenMidY = camera.getScreenMidY();
        for(int i = 0; i<row;i++){
            int worldY = i*tileSize;
            int screenY = worldY - cameraY + screenMidY;
            for(int j = 0; j<collumn; j++){
                int worldX = j*tileSize;
                int screenX = worldX - cameraX + screenMidX;
                if(worldX>cameraX-screenMidX -1*tileSize&&
                worldX<cameraX+screenMidX+3*tileSize&&
                worldY>cameraY-screenMidY-1*tileSize&&
                worldY<cameraY+screenMidY+3*tileSize){
                    graphics2d.drawImage(tiles[map[i][j]].image,screenX,screenY,tileSize,tileSize,null);
                }
            }
        }
        graphics2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawString("Best fit: " + gameWindow.genAlg.bestFit, 10, 20);
        graphics2d.drawString("Gen. No: " + gameWindow.genAlg.generationNumber, 10, 40);
        graphics2d.drawString("Population Size: " + gameWindow.genAlg.POPULATION, 10, 60);
        graphics2d.drawString("Instruction Size: " + gameWindow.genAlg.instructionSize, 10, 80);
    }
    public void draw(Graphics2D graphics2d,Camera camera, Agent agent){ //Draws the map around the agent
        camera.setWorldX(agent.agentX);
        camera.setWorldY(agent.agentY);
        int cameraX = camera.getWorldX();
        int cameraY = camera.getWorldY();
        int screenMidX = camera.getScreenMidX();
        int screenMidY = camera.getScreenMidY();

        for(int i = 0; i<row;i++){
            int worldY = i*tileSize;
            int screenY = worldY - cameraY + screenMidY;
            for(int j = 0; j<collumn; j++){
                int worldX = j*tileSize;
                int screenX = worldX - cameraX + screenMidX;
                if(worldX>cameraX-screenMidX -1*tileSize&&
                worldX<cameraX+screenMidX+3*tileSize&&
                worldY>cameraY-screenMidY-1*tileSize&&
                worldY<cameraY+screenMidY+3*tileSize){
                    graphics2d.drawImage(tiles[map[i][j]].image,screenX,screenY,tileSize,tileSize,null);
                }
            }
        }
        graphics2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawString("Best fit: " + gameWindow.genAlg.bestFit, 10, 20);
        graphics2d.drawString("Gen. No: " + gameWindow.genAlg.generationNumber, 10, 40);
        graphics2d.drawString("Population Size: " + gameWindow.genAlg.POPULATION, 10, 60);
        graphics2d.drawString("Instruction Size: " + gameWindow.genAlg.instructionSize, 10, 80);
    }
}
