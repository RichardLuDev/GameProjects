package richardludev.utilities.matrices;

import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {

    private static double x1 = 10, y1 = 20, x2 = 30, y2 = 40;
    
    @Test
    public void constructor_default() {
        Vector2D output = new Vector2D();
        
        Assert.assertEquals("Unexpected x value.", 0, output.getX(), 0);
        Assert.assertEquals("Unexpected y value.", 0, output.getY(), 0);
    }
    
    @Test
    public void constructor_validData() {
        Vector2D output = new Vector2D(x1, y1);
        
        Assert.assertEquals("Unexpected x value.", x1, output.getX(), 0);
        Assert.assertEquals("Unexpected y value.", y1, output.getY(), 0);
    }
    
    @Test
    public void equals_false(){
        Vector2D vector1 = new Vector2D(x1, y1);
        Vector2D vector2 = new Vector2D(x2, y2);
        
        Assert.assertFalse(vector1.equals(vector2));
    }
    
    @Test
    public void equals_true(){
        Vector2D vector1 = new Vector2D(x1, y1);
        Vector2D vector2 = new Vector2D(x1, y1);
        
        Assert.assertTrue(vector1.equals(vector2));
    }
    
    @Test
    public void add_twoVectors() {
        Vector2D vector1 = new Vector2D(x1, y1);
        Vector2D vector2 = new Vector2D(x2, y2);
        
        Vector2D output = vector1.add(vector2);
        
        Vector2D expected = new Vector2D(x1+x2, y1+y2);
        Assert.assertEquals(expected, output);
    }

    @Test
    public void subtract_largeBySmall() {
        Vector2D vector1 = new Vector2D(x1, y1);
        Vector2D vector2 = new Vector2D(x2, y2);
        
        Vector2D output = vector2.subtract(vector1);
        
        Vector2D expected = new Vector2D(x2-x1, y2-y1);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void subtract_smallByLarge() {
        Vector2D vector1 = new Vector2D(x1, y1);
        Vector2D vector2 = new Vector2D(x2, y2);
        
        Vector2D output = vector1.subtract(vector2);
        
        Vector2D expected = new Vector2D(x1-x2, y1-y2);
        Assert.assertEquals(expected, output);
    }
}
