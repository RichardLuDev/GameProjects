package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import richardludev.physics.PhysicsEngine;

/**
 * @author Richard Lu
 */
public class PhysicsGame {
    
    private static final int TIME_STEP = 50;
    
    private Frame frame;
    private Canvas canvas;
    
    private EntityManager entityManager;
    
    private PhysicsGameGraphicsSystem graphicsSystem;
    private PhysicsEngine physicsEngine;

    public static void main(String[] args) {
        
        PhysicsGame game = new PhysicsGame();
        game.run();
    }

    public PhysicsGame() {
        entityManager = new EntityManager();
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
            graphicsSystem.update(entityManager);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  
    private void setupPhysics(){
        physicsEngine = new PhysicsEngine(entityManager);
        PhysicsGameScenarioGenerator.GenerateOrbitScenario(entityManager, physicsEngine);
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
