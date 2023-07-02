package JavaRacer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean wPressed, sPressed, aPressed, dPressed, hPressed;
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                wPressed = true;
                break;
            case KeyEvent.VK_A:
                aPressed = true;
                break;
        
            case KeyEvent.VK_S:
                sPressed = true;
                break;
        
            case KeyEvent.VK_D:
                dPressed = true;
                break;
        
            case KeyEvent.VK_H:
                hPressed = true;
                break;
        
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                wPressed = false;
                break;
            case KeyEvent.VK_A:
                aPressed = false;
                break;
        
            case KeyEvent.VK_S:
                sPressed = false;
                break;
        
            case KeyEvent.VK_D:
                dPressed = false;
                break;
        
            case KeyEvent.VK_H:
                hPressed = false;
                break;
        
            default:
                break;
        }
    }
    
}
