package richardludev.physicsgame;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * @author Richard Lu
 */

//TODO: Temporary hardcoded sprite class to test out UI code.
public class PhysicsGameSprite {

    double x, y, dx, dy;
    int radius;
    
    public PhysicsGameSprite(double x, double y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        
        Random random = new Random();
        dx = random.nextDouble() + random.nextInt(5);
        dy = random.nextDouble() + random.nextInt(5);
    }
    
    public void update(){
        x += dx;
        y += dy;
    }
    
    public void draw(Graphics2D g){
        g.drawOval((int)x, (int)y, radius, radius);
    }
}
