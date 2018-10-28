package ex_7_binarySearch;

public class Logarithm implements LogarithmInterface {
    private double x;
    private double epsilon;

    public Logarithm(double x, double epsilon){
        this.x = x;
        this.epsilon = epsilon;
    }

    public double logarithmIterative() { return this.logarithmIterative(this.x, this.epsilon); }

    public double logarithmRecursive() { return this.logarithmRecursive(this.x, this.x, this.x, this.epsilon); }

}
