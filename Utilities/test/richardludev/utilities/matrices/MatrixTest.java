package richardludev.utilities.matrices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixTest {
    
    private double[][] data3x4A, data3x4B, data3x3, data2DTransformation, data4x3, data3x4A_times_data4x3, dataInvalid, dataEmpty;
    private double[] dataVector2D, dataVector2DInvalid, dataVectorEmpty;
    
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
        
        data2DTransformation = new double[][]{
                {1, 0, 3},
                {0, 1, 6},
                {0, 0, 1}
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
        
        dataVector2D = new double[] {10, 20, 1};
        
        dataVector2DInvalid = new double[] {10, 20, 2};
        
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
    
    @Test
    public void equals_notSameSize(){
        Matrix matrix3x4 = new Matrix(data3x4A);
        Matrix matrix3x3 = new Matrix(data3x3);
        
        Assert.assertFalse(matrix3x4.equals(matrix3x3));
    }
    
    @Test
    public void equals_sameSizeDifferentData(){
        Matrix matrix3x4A = new Matrix(data3x4A);
        Matrix matrix3x4B = new Matrix(data3x4B);
        
        Assert.assertFalse(matrix3x4A.equals(matrix3x4B));
    }
    
    @Test
    public void equals_sameSizeSameData(){
        Matrix matrix3x4A1 = new Matrix(data3x4A);
        Matrix matrix3x4A2 = new Matrix(data3x4A);
        
        Assert.assertTrue(matrix3x4A1.equals(matrix3x4A2));
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
        
        double[][] expectedData = new double[3][4];
        for(int i = 0; i < expectedData.length; i++){
            for(int j = 0; j < expectedData[0].length; j++){
                expectedData[i][j] = data3x4A[i][j] + data3x4B[i][j];
            }
        }
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
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
        
        double[][] expectedData = new double[3][4];
        for(int i = 0; i < expectedData.length; i++){
            for(int j = 0; j < expectedData[0].length; j++){
                expectedData[i][j] = data3x4A[i][j] - data3x4B[i][j];
            }
        }
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
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

        Matrix output = matrix3x4A.multiply(matrix4x3);
        
        Matrix expected = new Matrix(data3x4A_times_data4x3);
        Assert.assertEquals(expected, output);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void transform_notCorrectMatrixSize(){
        Matrix matrix3x4A = new Matrix(data3x4A);
        Vector2D vector = new Vector2D(dataVector2D[0], dataVector2D[1]);
        
        matrix3x4A.transform(vector);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void transform_notCorrectMatrixContent(){
        Matrix matrix3x3 = new Matrix(data3x3);
        Vector2D vector = new Vector2D(dataVector2D[0], dataVector2D[1]);
        
        matrix3x3.transform(vector);
    }
    
    public void transform_isTransformationMatrix(){
        Matrix matrix3x3 = new Matrix(data2DTransformation);
        Vector2D vector = new Vector2D(dataVector2D[0], dataVector2D[1]);
        
        Vector2D output = matrix3x3.transform(vector);
        
        Vector2D expected = new Vector2D(dataVector2D[0] + data2DTransformation[0][2], 
                                       dataVector2D[1] + data2DTransformation[1][2]);
        Assert.assertEquals(expected, output);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void toVector2D_notCorrectMatrixSize(){
        Matrix matrix = new Matrix(data3x3);
        matrix.toVector2D();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void toVector2D_notCorrectMatrixContent(){
        Matrix matrix = Matrix.columnVector(dataVector2DInvalid);
        matrix.toVector2D();
    }
    
    @Test
    public void toVector2D_correctMatrixContent(){
        Matrix matrix = Matrix.columnVector(dataVector2D);
        
        Vector2D output = matrix.toVector2D();
        
        Assert.assertEquals(matrix.getElement(0, 0), output.x, 0);
        Assert.assertEquals(matrix.getElement(1, 0), output.y, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void columnVector_noData(){        
        Matrix.columnVector(dataVectorEmpty);
    }
    
    @Test
    public void columnVector_sizeThree(){        
        Matrix output = Matrix.columnVector(dataVector2D);
        
        double[][] expectedData = {
                {dataVector2D[0]},
                {dataVector2D[1]}, 
                {dataVector2D[2]}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void rowVector_noData(){        
        Matrix.rowVector(dataVectorEmpty);
    }
    
    @Test
    public void rowVector_sizeThree(){        
        Matrix output = Matrix.rowVector(dataVector2D);
        
        double[][] expectedData = {
                {dataVector2D[0], dataVector2D[1], dataVector2D[2]}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
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
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void identity_sizeThreeTransformVector(){
        Vector2D originalVector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix identity = Matrix.identity(dataVector2D.length);
        
        Vector2D output = identity.transform(originalVector);
        
        Assert.assertEquals(originalVector, output);
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
        Assert.assertEquals(expected, output);
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
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void rotational_positiveAngle_transformVector(){
        double angle = 1;
        Vector2D vector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix rotational = Matrix.rotational(angle);
        
        Vector2D output = rotational.transform(vector);
        
        double epsilonOverride = 1E-3; //too lazy to grab more decimals for the expected result :)
        Vector2D expected = Matrix.columnVector(new double[] {-11.426, 19.221, 1}).toVector2D();
        Assert.assertTrue(output.equals(expected, epsilonOverride));
    }
    
    @Test
    public void rotational_positiveAngleAboutPoint(){
        double angle = 0.5;
        double dx = 3;
        double dy = 4.5;
        
        Matrix output = Matrix.rotational(angle, dx, dy);
        
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        double[][] expectedData = {
                {cosAngle, -sinAngle, dx - cosAngle*dx + sinAngle*dy},
                {sinAngle, cosAngle, dy - sinAngle*dx - cosAngle*dy}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void rotational_negativeAngleAboutPoint(){
        double angle = -1.5;
        double dx = 3;
        double dy = 4.5;
        
        Matrix output = Matrix.rotational(angle, dx, dy);
        
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        double[][] expectedData = {
                {cosAngle, -sinAngle, dx - cosAngle*dx + sinAngle*dy},
                {sinAngle, cosAngle, dy - sinAngle*dx - cosAngle*dy}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void rotational_positiveAngleAboutPoint_transformVector(){
        double angle = 1;
        double dx = 3;
        double dy = 4.5;
        Vector2D vector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix rotational = Matrix.rotational(angle, dx, dy);
        
        Vector2D output = rotational.transform(vector);
        
        double epsilonOverride = 1E-3; //too lazy to grab more decimals for the expected result :)
        Vector2D expected = Matrix.columnVector(new double[] {-6.26068, 18.765, 1}).toVector2D();
        Assert.assertTrue(output.equals(expected, epsilonOverride));
    }
    
    @Test
    public void translational_nonzero(){
        double dx = 10;
        double dy = 39;
        Matrix output = Matrix.translational(dx, dy);
        
        double[][] expectedData = {
                {1, 0, 10},
                {0, 1, 39}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void translational_nonzero_transformVector(){
        double dx = 10;
        double dy = 39;
        Vector2D vector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix translational = Matrix.translational(dx, dy);
        
        Vector2D output = translational.transform(vector);
        
        Vector2D expected = Matrix.columnVector(new double[] {dataVector2D[0] + dx, dataVector2D[1] + dy, 1}).toVector2D();
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void scaling_zero(){
        double scaleX = 0;
        double scaleY = 0;
        Matrix output = Matrix.scaling(scaleX, scaleY);
        
        double[][] expectedData = {
                {0, 0, 0},
                {0, 0, 0}, 
                {0, 0, 1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void scaling_zero_transformVector(){
        double scaleX = 0;
        double scaleY = 0;
        Vector2D vector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix scaling = Matrix.scaling(scaleX, scaleY);
        
        Vector2D output = scaling.transform(vector);
        
        Vector2D expected = Matrix.columnVector(new double[] {0, 0, 1}).toVector2D();
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void scaling_lessThanOne(){
        double scaleX = 0.5;
        double scaleY = 0.4;
        Matrix output = Matrix.scaling(scaleX, scaleY);
        
        double[][] expectedData = {
                {scaleX, 0,   0},
                {0,   scaleY, 0}, 
                {0,   0,   1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void scaling_lessThanOne_transformVector(){
        double scaleX = 0.5;
        double scaleY = 0.4;
        Vector2D vector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix scaling = Matrix.scaling(scaleX, scaleY);
        
        Vector2D output = scaling.transform(vector);
        
        Vector2D expected = Matrix.columnVector(new double[] {dataVector2D[0]*scaleX, dataVector2D[1]*scaleY, 1}).toVector2D();
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void scaling_greaterThanOne(){
        double scaleX = 10;
        double scaleY = 5;
        Matrix output = Matrix.scaling(scaleX, scaleY);
        
        double[][] expectedData = {
                {scaleX, 0,   0},
                {0,   scaleY, 0}, 
                {0,   0,   1}};
        Matrix expected = new Matrix(expectedData);
        Assert.assertEquals(expected, output);
    }
    
    @Test
    public void scaling_greaterThanOne_transformVector(){
        double scaleX = 10;
        double scaleY = 5;
        Vector2D vector = Matrix.columnVector(dataVector2D).toVector2D();
        Matrix scaling = Matrix.scaling(scaleX, scaleY);
        
        Vector2D output = scaling.transform(vector);
        
        Vector2D expected = Matrix.columnVector(new double[] {dataVector2D[0]*scaleX, dataVector2D[1]*scaleY, 1}).toVector2D();;
        Assert.assertEquals(expected, output);
    }
}
