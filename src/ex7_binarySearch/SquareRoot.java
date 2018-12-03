package ex7_binarySearch;

public class SquareRoot implements SquareRootInterface {
    private double x;
    private double epsilon;

    public SquareRoot(double x, double epsilon){
        this.x = x;
        this.epsilon = epsilon;
    }

    public double squareRootIterative() { return this.sqrtIterative(this.x, this.epsilon); }

    public double squareRootRecursive() { return this.sqrtRecursive(this.x, this.x, this.x, this.epsilon); }

}
