
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
