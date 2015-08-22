package richardludev.componentmodel;

public class Position2DComponent extends ComponentBase{

    double x,y;
    
    public Position2DComponent(long id) {
        super(id);
    }
    
    public double getX(){
        return x;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
}
