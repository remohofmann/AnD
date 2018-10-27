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

    default double sqrtRecursive(double x, double mid, double epsilon) {
        double result = 0;
        if(Math.abs(result*result - x) < epsilon) return result;

        if(mid*mid > x){
            System.out.println("bigger");
            System.out.println("result: " + result);
            System.out.println("mid: " + mid);
            result = result + sqrtRecursive(x, mid/2, epsilon);
        }
        else {
            System.out.println("smaller");
            System.out.println("result: " + result);
            System.out.println("mid: " + mid);
            result = result + mid;
            result = result + sqrtRecursive(x, mid/2, epsilon);
        }


        return result;
    }
}









