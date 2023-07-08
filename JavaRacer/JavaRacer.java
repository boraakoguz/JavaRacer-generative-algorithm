package JavaRacer;
import javax.swing.JFrame;

public class JavaRacer {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Java Racer");
        window.setResizable(false);

        GameWindow gameWindow = new GameWindow();

        window.add(gameWindow);
        window.pack();
        window.setVisible(true);
        gameWindow.startGameLoop();
    }
}