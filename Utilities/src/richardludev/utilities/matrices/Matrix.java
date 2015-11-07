package richardludev.utilities.matrices;

public class Matrix {
    
    static final double EPSILON = 1E-9;
    
    private final double[][] data;
    private final int m, n;
    
    //Public Methods
    
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
    
    public Vector2D transform(Vector2D v){
        if (this.is2DTransformationMatrix()){
            Vector2D out = new Vector2D();
            out.x = this.data[0][0]*v.x + this.data[0][1]*v.y + this.data[0][2];
            out.y = this.data[1][0]*v.x + this.data[1][1]*v.y + this.data[1][2];
            return out; 
        }
        else{
            throw new IllegalArgumentException("This matrix cannot transform a 2D vector.");
        }
    }
    
    public Vector2D toVector2D(){
        if(this.canCastToVector2D()){
            return new Vector2D(this.data[0][0], this.data[1][0]);
        }
        else{
            throw new IllegalArgumentException("This matrix cannot be cast into a Vector");
        }
    }
    
    public boolean is2DTransformationMatrix(){
        //needs to be size 3x3
        if(this.m != 3 || this.n != 3) return false;
        
        //last row needs to have values (0,0,1)
        if(Math.abs(this.data[2][0]) > EPSILON ) return false;
        if(Math.abs(this.data[2][1]) > EPSILON ) return false;
        if(Math.abs(this.data[2][2] - 1) > EPSILON ) return false;
        return true;
    }
    
    public boolean canCastToVector2D(){
        //needs to be size 3x1
        if(this.m != 3 || this.n != 1) return false;
        
        //last value needs to be 1
        if(Math.abs(this.data[2][0] - 1) > EPSILON ) return false;
        return true;
    }
    
    @Override
    public boolean equals(Object other){
        return this.equals((Matrix)other, EPSILON);
    }
    
    public boolean equals(Matrix other, double epsilon){
        if(this.m != other.m) return false;
        if(this.n != other.n) return false;
        
        for(int i = 0; i < this.m; i++){
            for(int j = 0; j < this.n; j++){
                if(Math.abs(this.data[i][j] - other.data[i][j]) > epsilon) return false;
            }
        }
        return true;
    }
    
    //Static Helpers
    
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
