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
  //boolean[] activation = new boolean[]{false,false,false,false,false,false};//{No activation, tanh, sin, cos, ln,}
  table = new float[m][n];
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
        table[i][j]=0; 
        }
    }
  }
  //Function derivating anny given matrix
  static public Matrix derive(Matrix A, int activ){
    Matrix B = new Matrix(A.m,A.n);
    switch(activ){
      case 1:
      B = dtanh(B);
      break;
      case 2:
      B = dsin(B);
      break;
      case 3:
      B = dcos(B);
      break;
      case 4:
      B = dln(B);
      break;
      case 5:
      B = dgaussian(B);
      break;
  }
  return B;
  }
  //Function transposing anny given matrix
  static public Matrix transpose(Matrix A){
    Matrix O = new Matrix(A.n,A.m);
    for(int i = 0; i < O.m; i++){
      for(int j = 0; j < O.n; j++){
        O.table[i][j]=A.table[j][i];
      }
    }
    return O;
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
  static public Matrix mMult(Matrix A, Matrix B,int activ) {
    switch(activ){
      case 1:
      B = tanh(B);
      break;
      case 2:
      B = sin(B);
      break;
      case 3:
      B = cos(B);
      break;
      case 4:
      B = ln(B);
      break;
      case 5:
      B = gaussian(B);
      break;
  }
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
  //Applies gaussian function to the matrix
  static public Matrix gaussian(Matrix A){
    for(int i = 0; i < A.m; i++){
        for(int j = 0; j < A.n; j++){
          A.table[i][j] = (float)(Math.exp(-(Math.pow(A.table[i][j],2f))));
      }
    }
    return A;
  }
  //Applies natural logarith function to the matrix
  static public Matrix ln(Matrix A){
    for(int i = 0; i < A.m; i++){
        for(int j = 0; j < A.n; j++){
          A.table[i][j] = (float)(Math.log(A.table[i][j]));
      }
    }
    return A;
  }
  //Applies cosin function to the matrix
  static public Matrix cos(Matrix A){
    for(int i = 0; i < A.m; i++){
        for(int j = 0; j < A.n; j++){
          A.table[i][j] = (float)(Math.cos(A.table[i][j]));
        }
       }
       return A;
  }
  //Applies sinus function to the matrix
  static public Matrix sin(Matrix A){
    for(int i = 0; i < A.m; i++){
        for(int j = 0; j < A.n; j++){
          A.table[i][j] = (float)(Math.sin(A.table[i][j]));
        }
      }
    return A;
  }
  //Function that computes the tanh value of a floating point number
  static public float tanh(float n){
    return (float)((Math.exp(n)-Math.exp(-n))/(Math.exp(n)+Math.exp(-n)));
  }
  //Function that applies the tanh(x) function to every number in a matrix
  static public Matrix tanh(Matrix A){
   for(int i = 0; i < A.m; i++){
    for(int j = 0; j < A.n; j++){
      A.table[i][j] = (float)(Math.exp((float)A.table[i][j])-Math.exp(((float)-A.table[i][j]))/(Math.exp((float)(A.table[i][j]))+Math.exp((float)(-A.table[i][j]))));
    }
   }
   return A;
  }
  //Derives the gaussian function
  static public Matrix dgaussian(Matrix A){
    for(int i = 0; i < A.m; i++){
      for(int j = 0; j < A.n; j++){
        A.table[i][j] = (float)(-2*A.table[i][j]*Math.exp(-Math.pow(A.table[i][j],2)));
      }
     }
     return A;
  }
  //Derives the ln function
  static public Matrix dln(Matrix A){
    for(int i = 0; i < A.m; i++){
      for(int j = 0; j < A.n; j++){
        A.table[i][j] = (float)(1/(A.table[i][j]));
      }
     }
     return A;
  }
  //Derives the cosine function
  static public Matrix dcos(Matrix A){
    for(int i = 0; i < A.m; i++){
      for(int j = 0; j < A.n; j++){
        A.table[i][j] = (float)(-Math.sin(A.table[i][j]));
      }
     }
     return A;
  }
  //Derives the sinus function
  static public Matrix dsin(Matrix A){
    for(int i = 0; i < A.m; i++){
      for(int j = 0; j < A.n; j++){
        A.table[i][j] = (float)(Math.cos(A.table[i][j]));
      }
     }
     return A;
  }
  //Derive the tanh function
  static public Matrix dtanh(Matrix A){
    for(int i = 0; i < A.m; i++){
      for(int j = 0; j < A.n; j++){
        A.table[i][j] = (float)(1-tanh(A.table[i][j])*tanh(A.table[i][j]));
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
