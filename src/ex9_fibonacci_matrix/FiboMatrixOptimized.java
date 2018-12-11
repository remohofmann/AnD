package ex9_fibonacci_matrix;

public class FiboMatrixOptimized {

    private long a, b, c;

    /**
     * Assumption:
     * base matrix is:
     * [0 1]
     * [1 1]
     * so:
     * [0 1]^2  =   [1 1]
     * [1 1]        [1 2]
     * and
     * [1 1]^2  =   [1 2]
     * [1 2]        [2 3]
     * and so on.
     * So, with an a,b,c matrix defined as:
     * [a b]
     * [b c]
     * matrix * base is:
     * [a b] [0 1]  =   [b c]
     * [b c] [1 1]      [c b+c]
     * So:
     * - calculation is reduced to an array of 3 items
     * - a -> b
     * - b -> c
     * - c -> b+c
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
        for (int i = 1; i < n; i++) {
            result.a = result.b;
            result.b = result.c;
            // c = b+c, where b is now saved in a, thus:
            result.c = result.a + result.c;
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
