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
    
    public TileManager(GameWindow gw){
        this.gameWindow = gw;
        tiles = new MapTiles[9];
        loadTileImages();
        map = MapLoader.loadMap();
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
    public void draw(Graphics2D graphics2d, Agent agent){ //Draws the map around the agent
        for(int i = 0; i<gameWindow.row;i++){
            int worldY = i*gameWindow.tileSize;
            int screenY = worldY - agent.agentY + agent.screenMidY;
            for(int j = 0; j<gameWindow.collumn; j++){
                int worldX = j*gameWindow.tileSize;
                int screenX = worldX - agent.agentX + agent.screenMidX;
                if(worldX>agent.agentX-agent.screenMidX -1*gameWindow.tileSize&&
                worldX<agent.agentX+agent.screenMidX+3*gameWindow.tileSize&&
                worldY>agent.agentY-agent.screenMidY-1*gameWindow.tileSize&&
                worldY<agent.agentY+agent.screenMidY+3*gameWindow.tileSize){
                    graphics2d.drawImage(tiles[map[i][j]].image,screenX,screenY,gameWindow.tileSize,gameWindow.tileSize,null);
                }
            }
        }
        graphics2d.setFont(new Font("TimesRoman", Font.BOLD, 30));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawString("Points: " + Integer.toString(agent.points), 10, 30);
        graphics2d.drawString("Laps: " + Integer.toString(agent.laps), 10, 60);
        graphics2d.drawString("Speed: " + Integer.toString((int)Math.floor(agent.velocity.netVelocity())), gameWindow.tileSize*25, 30);
        if(agent.isCollided){
            graphics2d.setColor(Color.red);
            graphics2d.drawString("-1000", agent.screenMidX, agent.screenMidY-50);
        }
    }
}
