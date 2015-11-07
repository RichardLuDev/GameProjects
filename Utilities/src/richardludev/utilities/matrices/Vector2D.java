package richardludev.utilities.matrices;

public class Vector2D {
    double x;
    double y;
    
    public Vector2D(){
        this(0,0);
    }
    
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    
    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    
    @Override
    public boolean equals(Object other){
        return this.equals((Vector2D) other, Matrix.EPSILON);
    }
    
    public boolean equals(Vector2D other, double epsilon){
        if(Math.abs(this.x - other.x) > epsilon) return false;
        if(Math.abs(this.y - other.y) > epsilon) return false;
        return true;
    }
}
