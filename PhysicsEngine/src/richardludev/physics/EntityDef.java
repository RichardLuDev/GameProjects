package richardludev.physics;

public class EntityDef {
    
    private long id;
    private double x, y;
    private double vx, vy;
    private double mass;
    
    public EntityDef(long id, double mass, double x, double y){
        this.id = id;
        this.mass = mass;
        this.x = x;
        this.y = y;
    }
    
    public EntityDef(long id, double mass, double x, double y, double vx, double vy){
        this(id, mass, x, y);
        this.vx = vx;
        this.vy = vy;
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
    
    public double getVX(){
        return vx;
    }
    
    public double getVY(){
        return vy;
    }
}
