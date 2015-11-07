package richardludev.utilities.componentmodel;

public class PositionComponent extends ComponentBase{

    private double x,y;
    
    public PositionComponent(long id) {
        super(id);
    }
    
    public double getX(){
        return x;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void addToX(double deltaX){
        this.x += deltaX;
    }
    
    public double getY(){
        return y;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public void addToY(double deltaY){
        this.y += deltaY;
    }
    
}
