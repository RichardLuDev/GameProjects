package richardludev.utilities.componentmodel;

public class MovementComponent extends ComponentBase{

    private double vx, vy, ax, ay;
    
    public MovementComponent(long id) {
        super(id);
    }

    public double getVX(){
        return this.vx;
    }
    
    public void setVX(double vx){
        this.vx = vx;
    }
    
    public void addToVX(double deltaVx){
        this.vx += deltaVx;
    }
    
    public double getVY(){
        return this.vy;
    }
    
    public void setVY(double vy){
        this.vy = vy;
    }
    public void addToVY(double deltaVy){
        this.vy += deltaVy;
    }
    
    public double getAX() {
        return ax;
    }

    public void setAX(double ax) {
        this.ax = ax;
    }
    
    public void addToAX(double deltaAx){
        this.ax += deltaAx;
    }

    public double getAY() {
        return ay;
    }

    public void setAY(double ay) {
        this.ay = ay;
    }
    
    public void addToAY(double deltaAy){
        this.ay += deltaAy;
    }
}
