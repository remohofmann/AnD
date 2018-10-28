package ex_7_binarySearch;

public interface LogarithmInterface {

    default double logarithmIterative(double x, double epsilon) {
        double result = 0;
        int i = 2;
        while(Math.abs(Math.pow(2, result)-x) > epsilon) {
            if(Math.pow(2, result) < x) result = result + x/i;
            else if (Math.pow(2, result) > x) result  = result - x/i;
            i = i*2;
        }
        return result;
    }

    default double logarithmRecursive(double possibleLog, double lastDiff, double targetValue, double epsilon) {
        double power = Math.pow(2, possibleLog);
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









