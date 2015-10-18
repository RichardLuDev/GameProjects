package richardludev.physics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ForceTest {

    private static final double epsilon = 10E-4;
    
    private double xComp, yComp;
    private Force force;
    
    @Before
    public void setUp(){
        xComp = 9001;
        yComp = 1337;
        force = new Force(xComp, yComp);
    }
    
    @Test
    public void getXComp_constructedValue(){
        double actual = force.getXComp();
        Assert.assertEquals(xComp, actual, epsilon);
    }
    
    @Test
    public void getYComp_constructedValue(){
        double actual = force.getYComp();
        Assert.assertEquals(yComp, actual, epsilon);
    }
}
