package ex_7_binarySearch;

public class SquareRoot implements SquareRootInterface {
    private double x;
    private double mid;
    private double epsilon;
    private double result;

    public SquareRoot(double x, double epsilon){
        this.x = x;
        this.epsilon = epsilon;
    }

    public SquareRoot(double x, double mid, double result, double epsilon){
        this(x, epsilon);
        this.mid = mid;
        this.result = result;
    }

    public double squareRootIterative() { return this.sqrtIterative(this.x, this.epsilon); }

    public double squareRootRecursive() { return this.sqrtRecursive(this.x, this.mid, this.epsilon); }

}
