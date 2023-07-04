package JavaRacer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GameWindow extends JPanel implements Runnable{
    public final int tileSize = 32;
    public final int collumn = 100;
    public final int row = 100;
    public final int WIDTH = tileSize*30;
    public final int HEIGHT = tileSize*20;
    private final int FPS = 60;
    Thread gameThread;
    KeyHandler keyHandle = new KeyHandler();
    TileManager tileManager = new TileManager(this);
    Agent[] agents = new Agent[10];
    CollisionControl collisionControl = new CollisionControl(this);
    Camera camera;
    GenerationAlgorithm genAlg;

    public GameWindow(){
        Color backgroundColor = new Color(34, 139, 34);
        for(int i = 0; i<agents.length;i++){
            agents[i] = new Agent(this);        
        }
        this.genAlg = new GenerationAlgorithm(agents);
        this.camera = new Camera(this,keyHandle, agents[0].agentX, agents[0].agentY);
        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandle);
        this.setFocusable(true);
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){
            //Update
            update();

            //Draw
            repaint();

            //set FPS
            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update(){
        for(int i = 0; i<agents.length;i++){
            agents[i].update();
        }
        genAlg.checkGeneration();
        camera.update();
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphic = (Graphics2D)graphics;

        tileManager.draw(graphic,camera);
        for (Agent agent : agents) {
            agent.draw(graphic);
        }

        graphic.dispose();
    }
    public int getTileSize(){
        return this.tileSize;
    }
    public int getRow(){
        return this.row;
    }
    public int getCollumn(){
        return this.collumn;
    }
}
