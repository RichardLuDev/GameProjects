package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Richard Lu
 */
public class PhysicsGameGraphicsEngine {
    
    private final int WINDOW_X = 300;
    private final int WINDOW_Y = 300;

    //TODO: May not need this lock if no immediately noticeable problems.
    private Object offScreenImageLock;
    private BufferedImage offscreenImage;
    
    private PhysicsGameCanvas gameCanvas;
    
    //TODO: Temporary Test Sprites
    PhysicsGameSprite[] gameSprites;
    
    public PhysicsGameGraphicsEngine(){
        
        offScreenImageLock = new Object();
        offscreenImage = new BufferedImage(WINDOW_X+10, WINDOW_Y+10, BufferedImage.TYPE_INT_RGB);
        
        gameSprites = new PhysicsGameSprite[10];
        for(int i = 0; i < gameSprites.length; i++){
            gameSprites[i] = new PhysicsGameSprite(0, 0, 20);
        }
        
        gameCanvas = new PhysicsGameCanvas(this);
        gameCanvas.setPreferredSize(new Dimension(WINDOW_X, WINDOW_Y));
        gameCanvas.setFocusable(true);
    }
    
    public void update(){
        populateOffscreenImage();
        gameCanvas.repaint();
    }
    
    public Canvas getGameCanvas(){
        return gameCanvas;
    }
    
    private void populateOffscreenImage(){
        Graphics2D offscreenGraphics = (Graphics2D)offscreenImage.getGraphics();
        for(PhysicsGameSprite gameSprite : gameSprites){
            gameSprite.update();
            gameSprite.draw(offscreenGraphics);
        }
    }
    
    private void drawOffscreenImage(Graphics g){
        g.drawImage(offscreenImage, 0, 0, null);
    }
    
    @SuppressWarnings("serial")
    private class PhysicsGameCanvas extends Canvas{
        
        PhysicsGameGraphicsEngine graphicsEngine;
        
        public PhysicsGameCanvas(PhysicsGameGraphicsEngine graphicsEngine) {
            this.graphicsEngine = graphicsEngine;
        }
        
        @Override
        public void update(Graphics g){
            paint(g);
        }
        
        @Override
        public void paint(Graphics g){
            graphicsEngine.drawOffscreenImage(g);
        }
    }
}
