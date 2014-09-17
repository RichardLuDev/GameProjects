package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Richard Lu
 */
public class PhysicsGameGraphicsEngine {
    
    private final int WINDOW_X = 300;
    private final int WINDOW_Y = 300;

    private BufferedImage offscreenImage;
    
    private PhysicsGameCanvas canvas;
    
    public PhysicsGameGraphicsEngine(){
        canvas = new PhysicsGameCanvas();
        canvas.setPreferredSize(new Dimension(WINDOW_X, WINDOW_Y));
        canvas.setFocusable(true);
    }
    
    public void update(){
        canvas.repaint();
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    private class PhysicsGameCanvas extends Canvas{
        @Override
        public void update(Graphics g){
            paint(g);
        }
        
        @Override
        public void paint(Graphics g){
            
        }
    }
}
