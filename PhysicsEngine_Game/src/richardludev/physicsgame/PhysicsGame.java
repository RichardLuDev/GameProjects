package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import richardludev.physics.EntityDef;
import richardludev.physics.ForceSourceGravity;
import richardludev.physics.IForceSourcePositionBased;
import richardludev.physics.PhysicsEngine;

/**
 * @author Richard Lu
 */
public class PhysicsGame {
    
    private static final int TIME_STEP = 100;
    
    private Frame frame;
    private Canvas canvas;
    
    private PhysicsGameGraphicsSystem graphicsSystem;
    private PhysicsEngine physicsEngine;

    public static void main(String[] args) {

        PhysicsGame game = new PhysicsGame();
        game.run();
    }

    public PhysicsGame() {
    }

    public void run() {
        setupPhysics();
        setupUI();
        displayUI();
        
        runGameLoop();
    }
    
    private void runGameLoop(){
        while(true){
            physicsEngine.update(TIME_STEP);
            graphicsSystem.update(physicsEngine);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  
    private void setupPhysics(){
        physicsEngine = new PhysicsEngine();
        
        //TODO: temporary generation of physics entities.
        Random random = new Random(9001);
        int mass = 10;
        for(long i = 0; i < 10; i++){
            int x = random.nextInt(PhysicsGameGraphicsSystem.WINDOW_X);
            int y = random.nextInt(PhysicsGameGraphicsSystem.WINDOW_Y);
            EntityDef entityDef = new EntityDef(i, mass, x, y);
            physicsEngine.addEntity(entityDef);
        }
        
        IForceSourcePositionBased forceSource = new ForceSourceGravity(PhysicsGameGraphicsSystem.WINDOW_X/2, PhysicsGameGraphicsSystem.WINDOW_Y/2, 100000);
        physicsEngine.addForceSource(forceSource);
    }
    
    private void setupUI(){
        graphicsSystem = new PhysicsGameGraphicsSystem();
        
        frame = new Frame("Physics Engine Game");
        canvas = graphicsSystem.getGameCanvas();
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        frame.add(canvas);
    }
    
    private void displayUI(){
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
