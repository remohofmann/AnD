package ex9_fibonacci_matrix;

public class FiboMatrixOptimized {

    private long a, b, c;

    /**
     * Assumption:
     * base matrix is:
     * [0 1]
     * [1 1]
     * where a,b,c matrix defined as:
     * [a b]
     * [b c]
     * thus, matrix * matrix is:
     * [b c]
     * [c b+c]
     */

    public FiboMatrixOptimized() {
        this.a = 0;
        this.b = 1;
        this.c = 1;
    }

    public FiboMatrixOptimized(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;

    }

    public FiboMatrixOptimized toPower(int n) {
        if (n == 0) return new FiboMatrixOptimized(1, 0, 1);
        else if (n == 1) return new FiboMatrixOptimized();
        FiboMatrixOptimized result = new FiboMatrixOptimized();
        long bTemporary;
        for (int i = 1; i < n; i++) {
            bTemporary = result.b;
            result.a = result.b;
            result.b = result.c;
            result.c = bTemporary + result.c;
        }
        return result;
    }

    @Override
    public String toString() {
        return "[" + this.a + " " + this.b + "]" + "\n" +
                "[" + this.b + " " + this.c + "]\n";
    }

    public long getFibo() {
        return this.b;
    }

    public long getFiboMinusOne() {
        return this.a;
    }

    public long getFiboPlusOne() {
        return this.c;
    }
}
