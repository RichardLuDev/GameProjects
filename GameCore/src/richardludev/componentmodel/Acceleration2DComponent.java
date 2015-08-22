package richardludev.componentmodel;

public class Acceleration2DComponent extends ComponentBase{
    
    double ax, ay;

    public Acceleration2DComponent(long id){
        super(id);
    }
    
    public double getAX() {
        return ax;
    }

    public void setAX(double ax) {
        this.ax = ax;
    }

    public double getAY() {
        return ay;
    }

    public void setAY(double ay) {
        this.ay = ay;
    }

}
