package MatrixMath;

import java.io.Serializable;
import java.util.Random;

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

    public Matrix(Vector[] v, boolean rows) {
        if (rows) {
            m = v.length;
            n = v[0].dimenssion;
            table = new float[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    table[i][j] = v[i].table[j];
                }
            }
        } else {
            n = v.length;
            m = v[0].dimenssion;
            table = new float[m][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    table[j][i] = v[i].table[j];
                }
            }
        }
    }

    //Constructor function for the Matrix class
    //Constructs an empty MxN matrix
    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        //boolean[] activation = new boolean[]{false,false,false,false,false,false};//{No activation, tanh, sin, cos, ln,}
        table = new float[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = 0;
            }
        }
    }

    //Function derivating anny given matrix
    static public Matrix derive(Matrix A, int activ) {
        Matrix B = new Matrix(A.m, A.n);
        switch (activ) {
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
    static public Matrix transpose(Matrix A) {
        Matrix O = new Matrix(A.n, A.m);
        for (int i = 0; i < O.m; i++) {
            for (int j = 0; j < O.n; j++) {
                O.table[i][j] = A.table[j][i];
            }
        }
        return O;
    }

    //Function that adds a randum number to each number in the matrix
    static public Matrix rAdd(Matrix m) {
        Random r = new Random();
        for (int i = 0; i < m.n; i++) {
            for (int j = 0; j < m.m; j++) {
                m.table[j][i] += (r.nextFloat() - 0.5f) * 0.1f;
            }
        }
        return m;
    }

    //Fuction that multiplies to matricies together
    static public Matrix mMult(Matrix A, Matrix B, int activ) {
        switch (activ) {
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
        Matrix o = new Matrix(A.m, B.n);
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < B.n; j++) {
                for (int k = 0; k < B.m; k++) {
                    o.table[i][j] += A.table[i][k] * B.table[k][j];
                }
            }
        }

        return o;
    }

    //Applies gaussian function to the matrix
    static public Matrix gaussian(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (Math.exp(-(Math.pow(A.table[i][j], 2f))));
            }
        }
        return A;
    }

    //Applies natural logarith function to the matrix
    static public Matrix ln(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (Math.log(A.table[i][j]));
            }
        }
        return A;
    }

    //Applies cosin function to the matrix
    static public Matrix cos(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (Math.cos(A.table[i][j]));
            }
        }
        return A;
    }

    //Applies sinus function to the matrix
    static public Matrix sin(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (Math.sin(A.table[i][j]));
            }
        }
        return A;
    }

    //Function that computes the tanh value of a floating point number
    static public float tanh(float n) {
        return (float) ((Math.exp(n) - Math.exp(-n)) / (Math.exp(n) + Math.exp(-n)));
    }

    //Function that applies the tanh(x) function to every number in a matrix
    static public Matrix tanh(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (Math.exp((float) A.table[i][j]) - Math.exp(((float) -A.table[i][j])) / (Math.exp((float) (A.table[i][j])) + Math.exp((float) (-A.table[i][j]))));
            }
        }
        return A;
    }

    //Derives the gaussian function
    static public Matrix dgaussian(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (-2 * A.table[i][j] * Math.exp(-Math.pow(A.table[i][j], 2)));
            }
        }
        return A;
    }

    //Derives the ln function
    static public Matrix dln(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (1 / (A.table[i][j]));
            }
        }
        return A;
    }

    //Derives the cosine function
    static public Matrix dcos(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (-Math.sin(A.table[i][j]));
            }
        }
        return A;
    }

    //Derives the sinus function
    static public Matrix dsin(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (Math.cos(A.table[i][j]));
            }
        }
        return A;
    }

    //Derive the tanh function
    static public Matrix dtanh(Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] = (float) (1 - tanh(A.table[i][j]) * tanh(A.table[i][j]));
            }
        }
        return A;
    }

    //Function that multiplies every number in a matrix by a number val
    static public Matrix nMult(float val, Matrix A) {
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                A.table[i][j] *= val;
            }
        }
        return A;
    }

    //Function that adds together every induvidual number in two matricies
    static public Matrix mAdd(Matrix A, Matrix B) {
        Matrix o = new Matrix(A.m, B.n);
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                o.table[i][j] = A.table[i][j] + B.table[i][j];
            }
        }
        return o;

    }

    //Function that subtracts every induvidual number in two matricies
    static public Matrix mSub(Matrix A, Matrix B) {
        return mAdd(A, nMult(-1f, B));
    }

    //Function that is used to cross two Neural networks based on the induviduals fitness
    static public Matrix nCross(Matrix A, Matrix B, int WeightA, int WeightB) {
        Matrix temp = new Matrix(A.m, A.n);
        float Amult = ((float) WeightA) / ((float) WeightA + (float) WeightB);
        float Bmult = ((float) WeightB) / ((float) WeightA + (float) WeightB);
        for (int i = 0; i < A.m; i++) {
            for (int j = 0; j < A.n; j++) {
                temp.table[i][j] = A.table[i][j] * Amult + B.table[i][j] * Bmult;
            }
        }
        return temp;
    }

    //Fuction that randomizes every number in a matrix
    public void randomize() {
        Random r = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = r.nextFloat() / 2;
            }
        }
    }

    //Function that returns an array of vectors
    public Vector[] rowVector() {
        Vector[] v = new Vector[m];
        for (int i = 0; i < m; i++) {
            float[] temp = new float[n];
            for (int j = 0; j < n; j++) {
                temp[j] = table[i][j];
            }
            v[i] = new Vector(temp);
        }
        return v;
    }

    //Function that returns an array of vectors
    public Vector[] colVector() {
        Vector[] v = new Vector[n];
        for (int i = 0; i < n; i++) {
            float[] temp = new float[m];
            for (int j = 0; j < m; j++) {
                temp[j] = table[j][i];
            }
            v[i] = new Vector(temp);
        }
        return v;
    }
    static public Vector[] rearange(Matrix inp){
        return inp.rowVector();
    }
    //Function that solves a system of equations
    static public Vector gaussElim(Matrix inp, Vector sol) {
        Vector[] m = inp.rowVector();
        if( m[0].table[0]==0)
            m=Matrix.rearange(inp);
        for(int i = 0; i < inp.n; i++)
            for(int j = i+1; j <m.length; j++){
                m[j] = Vector.add(Vector.nMult(-m[i].table[i],m[j]),Vector.nMult(m[j].table[i],m[i]));
                sol.table[i] =-sol.table[i]*sol.table[j]+sol.table[j]*sol.table[i];
            }
        for(int i = inp.n-1; i > -1; i--){
            for(int j = i+1; j <inp.m; j++){
                m[j] = Vector.add(Vector.nMult(-m[i].table[i],m[j]),Vector.nMult(m[j].table[i],m[i]));
                sol.table[i] =-sol.table[i]*sol.table[j]+sol.table[j]*sol.table[i];
            }
        }
        return new Matrix(m);
    }
}

public class Vector {
    int dimenssion;
    float[] table;

    public Vector(float[] table) {
        this.dimenssion = table.length;
        this.table = table;
    }

    public Vector(int l) {
        this.dimenssion = l;
        table = new float[l];
        for (int i = 0; i < l; i++) {
            table[l] = 0;
        }
    }

    public static Vector add(Vector A, Vector B) {
        if (A.dimenssion != B.dimenssion)
            return null;
        Vector temp = new Vector(A.dimenssion);
        for (int i = 0; i < A.dimenssion; i++) {
            temp.table[i] = A.table[i] + B.table[i];
        }
        return temp;
    }

    public static float scalarproduct(Vector A, Vector B) {
        if (A.dimenssion != B.dimenssion)
            return null;
        float temp = 0;
        for (int i = 0; i < A.dimenssion; i++) {
            temp = A.table[i] + B.table[i];
        }
        return temp;

    }

    public static Vector nMult(float n, Vector A) {
        Vector temp = new Vector(A.dimenssion);
        for (int i = 0; i < A.dimenssion; i++) {
            temp.table[i] = A.table[i] * n;
        }
        return temp;
    }

    public static Vector project(Vector A, Vector B) {
        if (A.dimenssion != B.dimenssion)
            return null;
        return Vector.nMult((Vector.scalarproduct(A, B) / Math.sqrt(Vector.scalarproduct(A, B))), B);;
    }

    public static float len(Vector A) {
        return Math.sqrt(Vector.scalarproduct(A, A));
    }

}
