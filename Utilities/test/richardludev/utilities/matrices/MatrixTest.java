package richardludev.utilities.matrices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixTest {
    
    private static final double EPSILON = 10E-4;
    
    private double[][] data3x4A, data3x4B, data3x3, dataInvalid, dataEmpty;
    private Matrix matrix1, matrix2;
    
    @Before
    public void setUp(){
        data3x4A = new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        
        data3x4B = new double[][]{
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2}
        };
        
        data3x3 = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        
        dataInvalid = new double[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2}
        };
        
        dataEmpty = new double[0][0];
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_notValidData() {
        new Matrix(dataInvalid);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_emptyData(){
        new Matrix(dataEmpty);
    }
    
    @Test
    public void constructor_goodData(){
        Matrix output = new Matrix(data3x4A);
        
        Assert.assertEquals("Unexpected number of rows.", output.getRowNum(), data3x4A.length);
        Assert.assertEquals("Unexpected number of columns.", output.getColNum(), data3x4A[0].length);
        
        for(int i = 1; i <= output.getRowNum(); i++){
            for(int j = 1; j <= output.getColNum(); j++){
                Assert.assertEquals("Unexpected value.", data3x4A[i-1][j-1], output.getElement(i, j), 0);
            }
        }
    }
    
    @Test
    public void identity_goodData(){
        int size = 3;
        double diagonalValue = 1;
        double nondiagonalValue = 0;
        
        Matrix output = Matrix.identity(size);
        
        Assert.assertEquals("Unexpected size.", output.getRowNum(), size);
        Assert.assertEquals("Unexpected size.", output.getColNum(), size);
        
        for(int i = 1; i <= output.getRowNum(); i++){
            for(int j = 1; j <= output.getColNum(); j++){
                if(i == j)
                    Assert.assertEquals("Unexpected diagonal value.", output.getElement(i, j), diagonalValue, 0);
                if(i != j)
                    Assert.assertEquals("Unexpected non-diagonal value.", output.getElement(i, j), nondiagonalValue, 0);
            }
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void add_differentSize(){
        Matrix matrix3x4 = new Matrix(data3x4A);
        Matrix matrix3x3 = new Matrix(data3x3);

        matrix3x4.add(matrix3x3);
    }
    
    @Test
    public void add_goodData(){
        Matrix matrix3x4A = new Matrix(data3x4A);
        Matrix matrix3x4B = new Matrix(data3x4B);
        
        Matrix output = matrix3x4A.add(matrix3x4B);
        
        Assert.assertEquals("Unexpected size.", matrix3x4A.getRowNum(), output.getRowNum());
        Assert.assertEquals("Unexpected size.", matrix3x4A.getColNum(), output.getColNum());
        
        for(int i = 1; i < output.getRowNum(); i++){
            for(int j = 1; j < output.getColNum(); j++){
                Assert.assertEquals(matrix3x4A.getElement(i, j) + matrix3x4B.getElement(i, j), output.getElement(i, j), EPSILON);
            }
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void subtract_differentSize(){
        Matrix matrix3x4 = new Matrix(data3x4A);
        Matrix matrix3x3 = new Matrix(data3x3);

        matrix3x4.subtract(matrix3x3);
    }
    
    @Test
    public void subtract_goodData(){
        Matrix matrix3x4A = new Matrix(data3x4A);
        Matrix matrix3x4B = new Matrix(data3x4B);
        
        Matrix output = matrix3x4A.subtract(matrix3x4B);
        
        Assert.assertEquals("Unexpected size.", matrix3x4A.getRowNum(), output.getRowNum());
        Assert.assertEquals("Unexpected size.", matrix3x4A.getColNum(), output.getColNum());
        
        for(int i = 1; i < output.getRowNum(); i++){
            for(int j = 1; j < output.getColNum(); j++){
                Assert.assertEquals(matrix3x4A.getElement(i, j) - matrix3x4B.getElement(i, j), output.getElement(i, j), EPSILON);
            }
        }
    }
}
