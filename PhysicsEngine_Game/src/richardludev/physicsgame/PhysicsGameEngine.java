package richardludev.physicsgame;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PhysicsGameEngine {

	private static final String WINDOW_TITLE = "Physics Game";
	private static final int FRAME_TIME_MILLI = 100;
	
	private PhysicsGamePanel panel;
	private UpdateGamePanel updateGamePanel;
	
	public PhysicsGameEngine(){
		setupUI();
	}
	
	public void execute() throws InterruptedException, InvocationTargetException{
		
		long startTimeMilli;
		long deltaTimeMilli;
		
		while(true){
			startTimeMilli = System.nanoTime() / 1000000;
			
			panel.increment(1, 1);
			
			SwingUtilities.invokeAndWait(updateGamePanel);
			
			deltaTimeMilli = (System.nanoTime() / 1000000) - startTimeMilli;
			
			if(deltaTimeMilli < FRAME_TIME_MILLI){
				Thread.sleep(FRAME_TIME_MILLI - deltaTimeMilli);
			}
		}
	}
	
	private void setupUI(){
		
		JFrame window = new JFrame();
		window.setTitle(WINDOW_TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new PhysicsGamePanel();
		
		updateGamePanel = new UpdateGamePanel(panel);
		
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}
	
	//Runnable class used to invoke UI update on the EDT.
	private class UpdateGamePanel implements Runnable{
		
		private PhysicsGamePanel panel;
		
		public UpdateGamePanel(PhysicsGamePanel panel){
			this.panel = panel;
		}
		
		public void run(){
			panel.repaint();
		}
	}
	
}
