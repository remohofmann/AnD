package ex7_binarySearch;

public class Logarithm implements LogarithmInterface {
    private double x, base;
    private double epsilon;

    public Logarithm(double x, double base, double epsilon){
        this.x = x;
        this.base = base;
        this.epsilon = epsilon;
    }

    public double logarithmIterative() { return this.logarithmIterative(this.x, this.base, this.epsilon); }

    public double logarithmRecursive() { return this.logarithmRecursive(this.x, this.x, this.x, this.base, this.epsilon); }

}
