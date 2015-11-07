package richardlude.utilities.componentmodel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import richardludev.utilities.componentmodel.MovementComponent;

public class MovementComponentTest {

    private static final double EPSILON = 10E-4;
    
    private static final int ID_1 = 1;
    private static final int ID_2 = 2;
    
    private MovementComponent moveComp1;
    private MovementComponent moveComp2;
    
    @Before
    public void setUp(){
        moveComp1 = new MovementComponent(ID_1);
        moveComp2 = new MovementComponent(ID_2);
    }
    
    @Test
    public void compareTo_otherLargerID(){
        int actual = moveComp1.compareTo(moveComp2);
        
        int expected = -1;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void compareTo_otherSmallerID(){
        int actual = moveComp2.compareTo(moveComp1);
        
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void compareTo_otherSameID(){
        int actual = moveComp1.compareTo(moveComp1);
        
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void setVX_getVX(){
        double vx = 10;
        
        moveComp1.setVX(vx);
        double actual = moveComp1.getVX();
        
        double expected = vx;
        Assert.assertEquals(expected, actual, EPSILON);
    }
    
    @Test
    public void addToVX_getVX(){
        double vx = 10;
        double vxDelta = 5;
        
        moveComp1.setVX(vx);
        moveComp1.addToAX(vxDelta);
        double actual = moveComp1.getVX();
        
        double expected = vx + vxDelta;
        Assert.assertEquals(expected, actual, EPSILON);
    }
}
