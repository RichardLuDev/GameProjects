package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import richardludev.physics.EntityDef;
import richardludev.physics.IForceSourcePositionBased;
import richardludev.physics.PhysicsEngine;

/**
 * @author Richard Lu
 */
public class PhysicsGameGraphicsSystem {
    
    public static final int WINDOW_X = 800;
    public static final int WINDOW_Y = 600;

    //TODO: May not need this lock if no immediately noticeable problems.
    private Object offScreenImageLock;
    private BufferedImage offscreenImage;
    
    private PhysicsGameCanvas gameCanvas;
    
    public PhysicsGameGraphicsSystem(){
        
        offScreenImageLock = new Object();
        offscreenImage = new BufferedImage(WINDOW_X+10, WINDOW_Y+10, BufferedImage.TYPE_INT_RGB);
        
        gameCanvas = new PhysicsGameCanvas(this);
        gameCanvas.setPreferredSize(new Dimension(WINDOW_X, WINDOW_Y));
        gameCanvas.setFocusable(true);
    }
    
    public void update(PhysicsEngine physicsEngine){
        populateOffscreenImage(physicsEngine);
        gameCanvas.repaint();
    }
    
    public Canvas getGameCanvas(){
        return gameCanvas;
    }
    
    private void populateOffscreenImage(PhysicsEngine physicsEngine){
        Graphics2D offscreenGraphics = (Graphics2D)offscreenImage.getGraphics();
        offscreenGraphics.clearRect(0, 0, offscreenImage.getWidth(), offscreenImage.getHeight());
     
        EntityDef[] entityDefs = physicsEngine.getEntities();
        for(EntityDef entityDef : entityDefs){
            offscreenGraphics.drawOval((int)entityDef.getX(), (int)entityDef.getY(), 
                                       (int)entityDef.getMass(), (int)entityDef.getMass());
        }
        
        IForceSourcePositionBased[] forceSources = physicsEngine.getForceSourcePositionBased();
        for(IForceSourcePositionBased forceSource : forceSources){
        }
    }
    
    private void drawOffscreenImage(Graphics g){
        g.drawImage(offscreenImage, 0, 0, null);
    }
    
    @SuppressWarnings("serial")
    private class PhysicsGameCanvas extends Canvas{
        
        PhysicsGameGraphicsSystem graphicsEngine;
        
        public PhysicsGameCanvas(PhysicsGameGraphicsSystem graphicsEngine) {
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
