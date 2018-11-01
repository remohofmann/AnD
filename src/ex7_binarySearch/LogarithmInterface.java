package ex7_binarySearch;

public interface LogarithmInterface {

    default double logarithmIterative(double x, double base, double epsilon) {
        double result = 0;
        int i = 2;
        while(Math.abs(Math.pow(base, result)-x) > epsilon) {
            if(Math.pow(base, result) < x) result = result + x/i;
            else if (Math.pow(base, result) > x) result  = result - x/i;
            i = i*2;
        }
        return result;
    }

    default double logarithmRecursive(double possibleLog, double lastDiff, double targetValue, double base, double epsilon) {
        double power = Math.pow(base, possibleLog);
        double newDiff = lastDiff/2;
        if (Math.abs(power - targetValue) < epsilon) return possibleLog;
        else if(power > targetValue){
            return logarithmRecursive(possibleLog-newDiff, newDiff, targetValue, base, epsilon);
        }
        else if (power < targetValue) {
            return logarithmRecursive(possibleLog + newDiff, newDiff, targetValue, base, epsilon);
        }
        return Double.parseDouble(null);
    }
}









