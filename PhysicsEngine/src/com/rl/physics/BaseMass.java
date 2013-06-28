package com.rl.physics;

public abstract class BaseMass {

	protected static final int STEP_DATA_SIZE = 2;
	
	protected double mass;
	protected double[] x = new double[STEP_DATA_SIZE];
	protected double[] y = new double[STEP_DATA_SIZE];
	protected double[] xVel = new double[STEP_DATA_SIZE];
	protected double[] yVel = new double[STEP_DATA_SIZE];
	protected double[] xAcc = new double[STEP_DATA_SIZE];
	protected double[] yAcc = new double[STEP_DATA_SIZE];
	
	protected double momentInertia;
	protected double[] angle = new double[STEP_DATA_SIZE];
	protected double[] angleVel = new double[STEP_DATA_SIZE];
	protected double[] angleAcc = new double[STEP_DATA_SIZE];
	
}
