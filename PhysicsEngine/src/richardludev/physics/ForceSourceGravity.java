package richardludev.physics;

import java.lang.Math;

public class ForceSourceGravity implements IForceSourcePositionBased {

    private static final double maxForce = 30;
    private double x, y, magnitude;
    
    public ForceSourceGravity(double x, double y, double magnitude) {
        this.x = x;
        this.y = y;
        this.magnitude = magnitude;
    }
    
    @Override
    public Force getForceOnEntity(double entityMass, double entityX, double entityY) {
        
        double distSq = (x-entityX)*(x-entityX) + (y-entityY)*(y-entityY);
        
        if (distSq == 0){
            return new Force(0,0);
        }
        
        double forceMag = magnitude*entityMass/distSq;
        
        if(forceMag > maxForce){forceMag = maxForce;}
        
        double angle = Math.atan2((y-entityY),(x-entityX));
        
        return new Force(forceMag*Math.cos(angle), forceMag*Math.sin(angle));
    }
}
