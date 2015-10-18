package richardludev.utilities.matrices;

public class Matrix {
    
    private final double[][] data;
    private final int m, n;
    
    public static Matrix identity(int n){
        Matrix I = new Matrix(n, n);
        for(int i = 0; i < n; i++){
            I.data[i][i] = 1;
        }
        return I;
    }
    
    public Matrix(int m, int n){
        this.m = m;
        this.n = n;
        this.data = new double[m][n];
    }
    
    public Matrix(double[][] data){
        if(data.length <= 0) throw new IllegalArgumentException("The 2D array has no data.");
        
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
    
    public int getRowNum(){
        return m;
    }
    
    public int getColNum(){
        return n;
    }
    
    public double getElement(int m, int n){
        return this.data[m-1][n-1];
    }
    
    public Matrix add(Matrix B){
        Matrix A = this;
        if(A.m != B.m || A.n != B.n) throw new IllegalArgumentException("The matrices are not the same size");
        
        Matrix C = new Matrix(this.m, this.n);
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
        
        Matrix C = new Matrix(this.m, this.n);
        for(int i = 0; i < A.m; i++){
            for(int j = 0; j < A.n; j++){
                C.data[i][j] = A.data[i][j] - B.data[i][j];
            }
        }
        return C;
    }
}
