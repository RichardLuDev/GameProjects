package com.rl.physicsgame;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Richard Lu
 */
public class PhysicsGameMain {

	public static void main (String [] args) throws InterruptedException, InvocationTargetException{
		
		PhysicsGameEngine engine = new PhysicsGameEngine();
		engine.execute();
	}
}
