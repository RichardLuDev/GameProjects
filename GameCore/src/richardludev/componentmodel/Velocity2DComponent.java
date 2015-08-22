package richardludev.componentmodel;

public class Velocity2DComponent extends ComponentBase{

    double vx, vy;
    
    public Velocity2DComponent(long id) {
        super(id);
    }

    public double getVX(){
        return this.vx;
    }
    
    public void setVX(double vx){
        this.vx = vx;
    }
    
    public double getVY(){
        return this.vy;
    }
    
    public void setVY(double vy){
        this.vy = vy;
    }
}
