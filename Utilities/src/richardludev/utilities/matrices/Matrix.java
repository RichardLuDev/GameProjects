package richardludev.utilities.matrices;

public class Matrix {
    
    private final double[][] data;
    private final int m, n;
    
    public Matrix(int m, int n){
        this.m = m;
        this.n = n;
        this.data = new double[m][n];
    }
    
    public Matrix(double[][] data){
        if(data == null || data.length <= 0) throw new IllegalArgumentException("The 2D array has no data.");
        
        this.m = data.length;
        this.n = data[0].length;
        this.data = new double[m][n];
        
        for(int i = 0; i< this.m; i++){
            if(data[i].length != this.n) throw new IllegalArgumentException("Not all elements of the 2D array have the same length.");
            for(int j = 0; j< this.n; j++){
                this.data[i][j] = data[i][j];
            }
        }
    }
    
    public int getNumOfRows(){
        return m;
    }
    
    public int getNumOfCols(){
        return n;
    }
    
    public double getElement(int m, int n){
        return this.data[m][n];
    }
    
    public double[][] getData(){
        double[][] outputData = new double[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                outputData[i][j] = this.data[i][j];
        }
        return outputData;
    }
    
    public Matrix add(Matrix B){
        Matrix A = this;
        if(A.m != B.m || A.n != B.n) throw new IllegalArgumentException("The matrices are not the same size");
        
        Matrix C = new Matrix(A.m, A.n);
        for(int i = 0; i < A.m; i++){
            for(int j = 0; j < A.n; j++){
                C.data[i][j] = A.data[i][j] + B.data[i][j];
            }
        }
        return C;
    }
    
    public Matrix subtract(Matrix B){
        Matrix A = this;
        if(A.m != B.m || A.n != B.n) throw new IllegalArgumentException("The matrices are not the same size");
        
        Matrix C = new Matrix(A.m, A.n);
        for(int i = 0; i < A.m; i++){
            for(int j = 0; j < A.n; j++){
                C.data[i][j] = A.data[i][j] - B.data[i][j];
            }
        }
        return C;
    }
    
    public Matrix multiply(Matrix B){
        Matrix A = this;
        if(A.n != B.m) throw new IllegalArgumentException("The matrices with the given sizes cannot be multiplied.");
        
        Matrix C = new Matrix(A.m, B.n);
        for(int i=0; i < C.m; i++){
            for(int j=0; j < C.n; j++){
                for(int k=0; k < A.n; k++){
                    C.data[i][j] += A.data[i][k]*B.data[k][j];
                }
            }
        }
        return C;
    }
    
    public boolean Equals(Matrix other){
        if(this.m != other.m) return false;
        if(this.n != other.n) return false;
        
        for(int i = 0; i < this.m; i++){
            for(int j = 0; j < this.n; j++){
                if(this.data[i][j] != other.data[i][j]) return false;
            }
        }
        return true;
    }
    
    public boolean Equals(Matrix other, double tolerance){
        if(this.m != other.m) return false;
        if(this.n != other.n) return false;
        
        for(int i = 0; i < this.m; i++){
            for(int j = 0; j < this.n; j++){
                if(Math.abs(this.data[i][j] - other.data[i][j]) > tolerance) return false;
            }
        }
        return true;
    }
    
    public static Matrix columnVector(double[] data){
        if(data.length <= 0) throw new IllegalArgumentException("The array has no data.");
        
        Matrix V = new Matrix(data.length, 1);
        for(int i=0; i < data.length; i++){
            V.data[i][0] = data[i];
        }
        return V;
    }
    
    public static Matrix rowVector(double[] data){
        if(data.length <= 0) throw new IllegalArgumentException("The array has no data.");
        
        Matrix V = new Matrix(1, data.length);
        for(int j=0; j < data.length; j++){
            V.data[0][j] = data[j];
        }
        return V;
    }
    
    public static Matrix identity(int n){      
        Matrix I = new Matrix(n, n);
        for(int i = 0; i < n; i++){
            I.data[i][i] = 1;
        }
        return I;
    }
    
    public static Matrix rotational(double theta){
        Matrix R = identity(3);
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        R.data[0][0] = cosTheta;
        R.data[0][1] = -sinTheta;
        R.data[1][0] = sinTheta;
        R.data[1][1] = cosTheta;
        
        return R;
    }
    
    public static Matrix rotational(double theta, double x, double y){
        Matrix R = identity(3);
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        R.data[0][0] = cosTheta;
        R.data[0][1] = -sinTheta;
        R.data[0][2] = x - cosTheta*x + sinTheta*y;
        R.data[1][0] = sinTheta;
        R.data[1][1] = cosTheta;
        R.data[1][2] = y - sinTheta*x - cosTheta*y;
        
        return R;
    }
    
    public static Matrix translational(double x, double y){
        Matrix T = identity(3);
        T.data[0][2] = x;
        T.data[1][2] = y;
        
        return T;
    }
    
    public static Matrix scaling(double tx, double ty){
        Matrix S = identity(3);
        S.data[0][0] = tx;
        S.data[1][1] = ty;
        
        return S;
    }
}
