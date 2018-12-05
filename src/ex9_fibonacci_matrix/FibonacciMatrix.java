package ex9_fibonacci_matrix;

public class FibonacciMatrix {

    private long[] line1, line2;

    public FibonacciMatrix() {
        this.line1 = new long[]{0, 0};
        this.line2 = new long[]{0, 0};
    }

    public FibonacciMatrix(long[] line1, long[] line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    public FibonacciMatrix toPower(int n) {
        if (n == 0) return new FibonacciMatrix(new long[]{1,0}, new long[]{0,1});

        FibonacciMatrix result = new FibonacciMatrix(
                new long[]{this.line1[0], this.line1[1]},
                new long[]{this.line2[0], this.line2[1]});

        FibonacciMatrix factor = new FibonacciMatrix(
                new long[]{this.line1[0], this.line1[1]},
                new long[]{this.line2[0], this.line2[1]});
        for (int i = 1; i < n; i++) {
            result = result.multiplyBy(factor);
        }
        return result;
    }

    // extremely general matrix multiplication! Could be implemented easier!
    // assumption is made that the factor DOES fit the matrix multiplication!
    // no error catching!
    public FibonacciMatrix multiplyBy(FibonacciMatrix factor) {
        FibonacciMatrix result = new FibonacciMatrix();
        result.line1[0] = this.line1[0] * factor.line1[0] + this.line1[1] * factor.line2[0];
        result.line1[1] = this.line1[0] * factor.line1[1] + this.line1[1] * factor.line2[1];
        result.line2[0] = this.line2[0] * factor.line1[0] + this.line2[1] * factor.line2[0];
        result.line2[1] = this.line2[0] * factor.line1[1] + this.line2[1] * factor.line2[1];
        return result;
    }

    @Override
    public String toString() {
        return "[" + this.line1[0] + " " + this.line1[1] + "]" + "\n" +
                "[" + this.line2[0] + " " + this.line2[1] + "]\n" ;
    }

    public long[] getLine1() {
        return this.line1;
    }

    public long[] getLine2() {
        return this.line2;
    }
}
