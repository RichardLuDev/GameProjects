package richardludev.componentmodel;

public class Position2DComponent extends ComponentBase{

    private double x,y;
    
    public Position2DComponent(long id) {
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
