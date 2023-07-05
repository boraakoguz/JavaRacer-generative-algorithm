package JavaRacer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class MapLoader {
    public static int spawnX, spawnY, spawnDirection; // 0: right, 1: up, 2: left, 3: down
    public static int[][] loadMap() {
        BufferedImage image;
        int[][] map = new int[100][100];
        Random rand = new Random();
        try {
            image = ImageIO.read(new File("source/track.png"));
            if(image.getHeight()== 100&& image.getWidth() == 100){
                for(int x = 0; x<100;x++){
                    for(int y = 0; y<100;y++){
                        int rgb = image.getRGB(x, y);
                        switch (rgb) {
                            case -16777216: //asphalt
                                map[y][x] = rand.nextInt(2, 5);
                                break;
                            case -1237980: //boundary
                                map[y][x] = 5;
                                break;
                            case -16735512: //water
                                map[y][x] = 7;
                                break;
                            case -4621737: //ground
                                map[y][x] = 8;
                                break;
                            case -1: //line
                                if(image.getRGB(x+1, y)==-8421505){
                                    spawnDirection = 0;
                                    spawnX = x;
                                    spawnY = y;
                                }
                                else if(image.getRGB(x, y-1)==-8421505){
                                    spawnDirection = 1;
                                    spawnX = x;
                                    spawnY = y;
                                }
                                else if(image.getRGB(x-1, y)==-8421505){
                                    spawnDirection = 2;
                                    spawnX = x;
                                    spawnY = y;
                                }
                                else if(image.getRGB(x, y+1)==-8421505){
                                    spawnDirection = 3;
                                    spawnX = x;
                                    spawnY = y;
                                }
                                map[y][x] = 6;
                                break;
                            case -8421505: // gray dot for spawn direction. filled with asphalt instead.
                                map[y][x] = rand.nextInt(2, 5);
                                break;
                            default: //grass
                                map[y][x] = rand.nextInt(0, 2);
                                break;
                        }
                    }
                }
            }
            else{
                System.out.println(image.getHeight() + " "+ image.getWidth(null));
                System.out.println("Invalid image size. Please make sure it is 100px x 100px");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
