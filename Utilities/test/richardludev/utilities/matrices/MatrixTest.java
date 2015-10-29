package richardludev.utilities.matrices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixTest {
    
    private static final double EPSILON = 10E-4;
    
    private double[][] data3x4A, data3x4B, data3x3, data4x3, data3x4A_times_data4x3, dataInvalid, dataEmpty;
    private double[] dataVector3, dataVectorEmpty;
    
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
        
        data4x3 = new double[][]{
                {2, 4, 6},
                {8, 10, 12},
                {14, 16, 18},
                {20, 22, 24}
        };
        
        data3x4A_times_data4x3 = new double[][]{
                {140, 160, 180},
                {316, 368, 420},
                {492, 576, 660},
        };
        
        dataInvalid = new double[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2}
        };
        
        dataEmpty = new double[0][0];
        
        dataVector3 = new double[] {10, 20, 1};
        
        dataVectorEmpty = new double[0];
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
        
        Assert.assertEquals("Unexpected number of rows.", output.getNumOfRows(), data3x4A.length);
        Assert.assertEquals("Unexpected number of columns.", output.getNumOfCols(), data3x4A[0].length);
        
        for(int i = 0; i < output.getNumOfRows(); i++){
            for(int j = 0; j < output.getNumOfCols(); j++){
                Assert.assertEquals("Unexpected value.", data3x4A[i][j], output.getElement(i, j), 0);
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
        
        Assert.assertEquals("Unexpected size.", matrix3x4A.getNumOfRows(), output.getNumOfRows());
        Assert.assertEquals("Unexpected size.", matrix3x4A.getNumOfCols(), output.getNumOfCols());
        
        for(int i = 0; i < output.getNumOfRows(); i++){
            for(int j = 0; j < output.getNumOfCols(); j++){
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
        
        Assert.assertEquals("Unexpected size.", matrix3x4A.getNumOfRows(), output.getNumOfRows());
        Assert.assertEquals("Unexpected size.", matrix3x4A.getNumOfCols(), output.getNumOfCols());
        
        for(int i = 0; i < output.getNumOfRows(); i++){
            for(int j = 1; j < output.getNumOfCols(); j++){
                Assert.assertEquals(matrix3x4A.getElement(i, j) - matrix3x4B.getElement(i, j), output.getElement(i, j), EPSILON);
            }
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void mutiply_incompatibleSize(){
        Matrix matrix3x4A = new Matrix(data3x4A);
        Matrix matrix3x3 = new Matrix(data3x3);
        
        matrix3x4A.multiply(matrix3x3);
    }
    
    @Test
    public void mutiply_goodData(){
        Matrix matrix3x4A = new Matrix(data3x4A);
        Matrix matrix4x3 = new Matrix(data4x3);
        Matrix expectedOutput = new Matrix(data3x4A_times_data4x3);
        
        Matrix output = matrix3x4A.multiply(matrix4x3);
        
        Assert.assertEquals("Unexpected size.", matrix3x4A.getNumOfRows(), output.getNumOfRows());
        Assert.assertEquals("Unexpected size.", matrix4x3.getNumOfCols(), output.getNumOfCols());
        
        for(int i = 1; i < output.getNumOfRows(); i++){
            for(int j = 1; j < output.getNumOfCols(); j++){
                Assert.assertEquals(expectedOutput.getElement(i, j), output.getElement(i, j), EPSILON);
            }
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void columnVector_noData(){        
        Matrix.columnVector(dataVectorEmpty);
    }
    
    @Test
    public void columnVector_sizeThree(){        
        Matrix output = Matrix.columnVector(dataVector3);
        
        double[][] expectedData = {
                {dataVector3[0]},
                {dataVector3[1]}, 
                {dataVector3[2]}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertTrue(output.Equals(expected, EPSILON));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void rowVector_noData(){        
        Matrix.rowVector(dataVectorEmpty);
    }
    
    @Test
    public void rowVector_sizeThree(){        
        Matrix output = Matrix.rowVector(dataVector3);
        
        double[][] expectedData = {
                {dataVector3[0], dataVector3[1], dataVector3[2]}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertTrue(output.Equals(expected, EPSILON));
    }
    
    @Test
    public void identity_sizeThree(){
        int size = 3;
        
        Matrix output = Matrix.identity(size);
        
        double[][] expectedData = {
                {1, 0, 0},
                {0, 1, 0}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertTrue(output.Equals(expected, EPSILON));
    }
    
    @Test
    public void identity_sizeThreeTransformVector(){
        Matrix vector = Matrix.columnVector(dataVector3);
        Matrix identity = Matrix.identity(dataVector3.length);
        
        Matrix output = identity.multiply(vector);
        
        Assert.assertTrue(output.Equals(vector, EPSILON));
    }
    
    @Test
    public void rotational_positiveAngle(){
        double angle = 1;
        Matrix output = Matrix.rotational(angle);
        
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        
        double[][] expectedData = {
                {cosAngle, -sinAngle, 0},
                {sinAngle, cosAngle, 0}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertTrue(output.Equals(expected, EPSILON));
    }
    
    @Test
    public void rotational_negativeAngle(){
        double angle = -2;
        Matrix output = Matrix.rotational(angle);
        
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        
        double[][] expectedData = {
                {cosAngle, -sinAngle, 0},
                {sinAngle, cosAngle, 0}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertTrue(output.Equals(expected, EPSILON));
    }
    
    @Test
    public void rotational_positiveAngleTransformVector(){
        double angle = 1;
        Matrix vector = Matrix.columnVector(dataVector3);
        Matrix rotational = Matrix.rotational(angle);
        
        Matrix output = rotational.multiply(vector);
        
        Matrix expected = Matrix.columnVector(new double[] {-11.426, 19.221, 1});
        Assert.assertTrue(output.Equals(expected, EPSILON));
    }
    
    @Test
    public void rotational_positiveAngleAboutPoint(){
        double angle = 0.5;
        double dx = 3;
        double dy = 4.5;
        
        Matrix output = Matrix.rotational(angle, dx, dy);
        double[][] outputData = output.getData();
        
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        
        double[][] expectedData = {
                {cosAngle, -sinAngle, dx - cosAngle*dx + sinAngle*dy},
                {sinAngle, cosAngle, dy - sinAngle*dx - cosAngle*dy}, 
                {0, 0, 1}};
        
        for(int i = 0; i < expectedData.length; i++){
            Assert.assertArrayEquals(expectedData[i], outputData[i], EPSILON);
        }
    }
    
    @Test
    public void rotational_negativeAngleAboutPoint(){
        double angle = -1.5;
        double dx = 3;
        double dy = 4.5;
        
        Matrix output = Matrix.rotational(angle, dx, dy);
        double[][] outputData = output.getData();
        
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        
        double[][] expectedData = {
                {cosAngle, -sinAngle, dx - cosAngle*dx + sinAngle*dy},
                {sinAngle, cosAngle, dy - sinAngle*dx - cosAngle*dy}, 
                {0, 0, 1}};
        
        for(int i = 0; i < expectedData.length; i++){
            Assert.assertArrayEquals(expectedData[i], outputData[i], EPSILON);
        }
    }
}
