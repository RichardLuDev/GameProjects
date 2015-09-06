package richardludev.physics;

public class Force {
    
    private double xComp,yComp;
    
    public Force(double xComp, double yComp){
        this.xComp = xComp;
        this.yComp = yComp;
    }
    
    public Force(Force force){
        this.xComp = force.xComp;
        this.yComp = force.yComp;
    }
    
    public double getXComp(){
        return this.xComp;
    }
    
    public double getYComp(){
        return this.yComp;
    }
}
