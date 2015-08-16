package richardludev.physics;

public class EntityDef {
    
    private long id;
    private double x, y;
    private double mass;
    
    public EntityDef(long id, double mass, double x, double y){
        this.id = id;
        this.mass = mass;
        this.x = x;
        this.y = y;
    }
    
    public long getID(){
        return id;
    }
    
    public double getMass(){
        return mass;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
}
