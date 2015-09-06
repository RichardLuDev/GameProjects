package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import richardludev.physics.PhysicsSystem;

/**
 * @author Richard Lu
 */
public class PhysicsGame {
    
    private static final int TIME_STEP_MS = 50;
    private static final double TIME_STEP_SEC = 0.001*TIME_STEP_MS;
    
    private Frame frame;
    private Canvas canvas;
    
    private EntityManager entityManager;
    
    private LogicSystem logicSystem;
    private GraphicsSystem graphicsSystem;
    private PhysicsSystem physicsSystem;

    public static void main(String[] args) {
        
        PhysicsGame game = new PhysicsGame();
        game.run();
    }

    public PhysicsGame() {
        entityManager = new EntityManager();
    }

    public void run() {
        
        setupSystems();
        setupUI();
        displayUI();
        
        PhysicsGameScenarioGenerator.GenerateOrbitScenario(entityManager, physicsSystem);
        
        runGameLoop();
    }
    
    private void runGameLoop(){
        while(true){
            logicSystem.update(TIME_STEP_SEC);
            physicsSystem.update(TIME_STEP_SEC);
            graphicsSystem.update(entityManager);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  
    private void setupSystems(){
        logicSystem = new LogicSystem(entityManager);
        physicsSystem = new PhysicsSystem(entityManager);
        graphicsSystem = new GraphicsSystem();
    }
    
    private void setupUI(){
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
