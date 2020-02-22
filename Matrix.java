package MatrixMath;
import java.util.Random;
import java.lang.Math;
import java.io.Serializable;
public class Matrix implements Serializable {
    /**
     *
     */
     private static final long serialVersionUID = 1L;
  //Data holding array of the matrix
  public float[][] table;
  //Row count of the matrix
  public int m;
  // Column count of the matrix
  public int n;
  //Constructor function for the Matrix class
  //Constructs an empty MxN matrix
  public Matrix(int m, int n) {
  this.m = m;
  this.n = n;
  table = new float[m][n];
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
        table[i][j]=0; 
        }
    }
  }
  //Function that adds a randum number to each number in the matrix
  static public Matrix rAdd(Matrix m){
    Random r = new Random();
    for(int i = 0; i < m.n; i++){
     for(int j = 0; j < m.m; j++){
      m.table[j][i]+=(r.nextFloat()-0.5f)*0.1f; 
     }
    }
    return m;
  }
  //Fuction that multiplies to matricies together
  static public Matrix mMult(Matrix A, Matrix B) {
    Matrix o  = new Matrix(A.m,B.n);
    for(int i = 0; i < A.m; i++){
       for(int j = 0; j < B.n; j++){
        for(int k  = 0; k < B.m; k++){
         o.table[i][j]+=A.table[i][k]*B.table[k][j]; 
        }
       }
    }
    return o;
  }
  //Function that applies the tanh(x) function to every number in a matrix
  static public Matrix tanh(Matrix A){
   for(int i = 0; i < A.m; i++){
    for(int j = 0; j < A.n; j++){
      A.table[i][j] = (float)(Math.exp((float)A.table[i][j])/(1+Math.exp((float)A.table[i][j])));
    }
   }
   return A;
  }
  //Fuction that randomizes every number in a matrix
  public void randomize(){
    Random r = new Random();
    for(int i = 0; i < m; i++){
     for(int j = 0; j < n ; j++){
      table[i][j]=r.nextFloat()/2; 
     }
    }
  }
  //Function that multiplies every number in a matrix by a number val
  static public Matrix nMult(float val, Matrix A){
   for(int i = 0; i < A.m; i++){
    for(int j = 0; j <A.n; j++){
     A.table[i][j]*=val; 
    }
   }
   return A;
  }
  //Function that adds together every induvidual number in two matricies
  static public Matrix mAdd(Matrix A,Matrix B){
    Matrix o  = new Matrix(A.m,B.n);
    for(int i = 0; i < A.m; i++){
       for(int j = 0; j < A.n; j++){
        o.table[i][j]=A.table[i][j]+B.table[i][j];
       }
    }
    return o;
    
  }
  //Function that subtracts every induvidual number in two matricies
  static public Matrix mSub(Matrix A, Matrix B){
    return mAdd(A,nMult(-1f,B));
  }
  //Function that is used to cross two Neural networks based on the induviduals fitness
  static public Matrix nCross(Matrix A, Matrix B, int WeightA, int WeightB){
    Matrix temp = new Matrix(A.m,A.n);
    float Amult = ((float)WeightA)/((float)WeightA+(float)WeightB);
    float Bmult = ((float)WeightB)/((float)WeightA+(float)WeightB);
    for(int i = 0; i < A.m; i++){
     for(int j = 0; j < A.n; j++){
      temp.table[i][j] = A.table[i][j]*Amult+B.table[i][j]*Bmult; 
     }
    }
    return temp;
  }
}
