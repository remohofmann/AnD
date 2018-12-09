package ex9_fibonacci_matrix;

public class FiboMatrixColumn {

    private long[] column0, column1;

    public FiboMatrixColumn() {
        this.column0 = new long[]{0, 0};
        this.column1 = new long[]{0, 0};
    }

    public FiboMatrixColumn(long[] column0, long[] column1) {
        this.column0 = column0;
        this.column1 = column1;
    }

    public FiboMatrixColumn toPower(int n) {
        // if n=0 return unit matrix
        if (n == 0) return new FiboMatrixColumn(new long[]{1,0}, new long[]{0,1});
        // if n=1, return entry matrix
        else if (n == 1) return new FiboMatrixColumn(this.column0, this.column1);
        // if n>=2, start calculating
        FiboMatrixColumn result = new FiboMatrixColumn(
                new long[]{this.column0[0], this.column0[1]},
                new long[]{this.column1[0], this.column1[1]});

        FiboMatrixColumn factor = new FiboMatrixColumn(
                new long[]{this.column0[0], this.column0[1]},
                new long[]{this.column1[0], this.column1[1]});
        for (int i = 1; i < n; i++) {
            result = result.multiplyBy(factor);
        }
        return result;
    }

    // extremely general matrix multiplication! Could be implemented easier!
    // assumption is made that the factor DOES fit the matrix multiplication!
    // no error catching!
    public FiboMatrixColumn multiplyBy(FiboMatrixColumn factor) {
        FiboMatrixColumn result = new FiboMatrixColumn();
        result.column0[0] = this.column0[0] * factor.column0[0] + this.column1[0] * factor.column0[1];
        result.column0[1] = this.column0[1] * factor.column0[0] + this.column1[1] * factor.column0[1];
        result.column1[0] = this.column0[0] * factor.column1[0] + this.column1[0] * factor.column1[1];
        result.column1[1] = this.column0[1] * factor.column1[0] + this.column1[1] * factor.column1[1];
        return result;
    }

    @Override
    public String toString() {
        return "[" + this.column0[0] + " " + this.column1[0] + "]" + "\n" +
                "[" + this.column0[1] + " " + this.column1[1] + "]\n" ;
    }

    public long[] getColumn0() {
        return this.column0;
    }

    public long[] getColumn1() {
        return this.column1;
    }
}
