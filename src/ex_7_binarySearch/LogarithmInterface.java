package ex_7_binarySearch;

public interface LogarithmInterface {

    default double logarithmIterative(double x, double epsilon) {
        double result = 0;
        int i = 2;
        while(Math.abs(Math.pow(result, 2)-x) > epsilon) {
            if(Math.pow(result, 2) < x) result = result + x/i;
            else if (Math.pow(result, 2) > x) result  = result - x/i;
            i = i*2;
        }
        return result;
    }

    default double logarithmRecursive(double possibleLog, double lastDiff, double targetValue, double epsilon) {
        double power = Math.pow(possibleLog, 2);
        double newDiff = lastDiff/2;
        if (Math.abs(power - targetValue) < epsilon) return possibleLog;
        else if(power > targetValue){
            return logarithmRecursive(possibleLog-newDiff, newDiff, targetValue, epsilon);
        }
        else if (power < targetValue) {
            return logarithmRecursive(possibleLog + newDiff, newDiff, targetValue, epsilon);
        }
        return Double.parseDouble(null);
    }
}









