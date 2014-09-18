package richardludev.physicsgame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Richard Lu
 */
public class PhysicsGame {
    
    private Frame frame;
    private Canvas canvas;
    
    private PhysicsGameGraphicsEngine graphicsEngine;

    public static void main(String[] args) {

        PhysicsGame game = new PhysicsGame();
        game.run();
    }

    public PhysicsGame() {
    }

    public void run() {
        setupUI();
        displayUI();
        
        runGameLoop();
    }
    
    private void runGameLoop(){
        while(true){
            graphicsEngine.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void setupUI(){
        graphicsEngine = new PhysicsGameGraphicsEngine();
        
        frame = new Frame("Physics Engine Game");
        canvas = graphicsEngine.getGameCanvas();
        
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
