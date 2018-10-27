package ex_7_binarySearch;

public interface SquareRootInterface {

    default double sqrtIterative(double x, double epsilon) {
        double result = 0;
        int i = 2;
        while(Math.abs(result*result-x)>epsilon) {
            if((result+x/i)*(result+x/i)<x) result = result + x/i;
            i = i*2;
        }
        return result;
    }

    default double sqrtRecursive(double possibleSqrt, double lastDiff, double targetValue, double epsilon) {
        double square = possibleSqrt*possibleSqrt;
        double newDiff = lastDiff/2;
        if (Math.abs(square - targetValue) < epsilon) return possibleSqrt;
        else if(square > targetValue){
            return sqrtRecursive(possibleSqrt-newDiff, newDiff, targetValue, epsilon);
        }
        else if (square < targetValue) {
            return sqrtRecursive(possibleSqrt + newDiff, newDiff, targetValue, epsilon);
        }
        return Double.parseDouble(null);
    }
}









