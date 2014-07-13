package richardludev.physicsgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @author Richard Lu
 */
public class PhysicsGamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final int CANVAS_X = 700;
	private static final int CANVAS_Y = 500;

	private int x,y;
	
	public PhysicsGamePanel(){
		this.x = 100;
		this.y = 100;
		this.setSize(700, 500);
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(CANVAS_X, CANVAS_Y);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, CANVAS_X, CANVAS_Y);
		g2d.drawOval(x, y, 20, 20);
	}
	
	public void increment(int incrementX, int incrementY){
		x += incrementX;
		y += incrementY;
	}
}
