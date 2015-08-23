package richardludev.componentmodel;

import javax.xml.soap.DetailEntry;

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
