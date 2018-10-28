package ex_7_binarySearch;

public class Main {

    public static void main(String[] args){
        int[] arr = new int[]{1, 4, 9, 16, 25, 4500};
        double epsilon = 0.001;

        System.out.println("Epsilon: " + epsilon);
        System.out.println();
        System.out.println("---- SQRT----");
        System.out.println("----Iterative Function----");
        for(int j = 0; j<arr.length; j++) {
            SquareRoot tmp = new SquareRoot(arr[j], epsilon);
            System.out.println("sqrt("+ arr[j]+") = " + tmp.squareRootIterative());
        }
        System.out.println();
        System.out.println("---- Recursive Function----");
        for (int j = 0; j < arr.length; j++) {
            SquareRoot tmp = new SquareRoot(arr[j], epsilon);
            System.out.println("sqrt(" + arr[j] + ") = " + tmp.squareRootRecursive());
        }


        /*************************** logarithm ***************************************/
        double logEpsilon = 0.000001;
        arr = new int[]{2, 4, 8, 16, 32, 64};
        double base = 2;
        System.out.println("Epsilon: " + logEpsilon);
        System.out.println();
        System.out.println("---- LOGARITHM----");
        System.out.println("----Iterative Function----");
        Logarithm tmp;
        for (int j = 0; j < arr.length; j++) {
            tmp = new Logarithm(arr[j], base, logEpsilon);
            System.out.println("logarithm-base-("+base+")(" + arr[j] + ") = " + tmp.logarithmIterative());
        }
        System.out.println();
        System.out.println("---- Recursive Function----");
        for (int j = 0; j < arr.length; j++) {
            tmp = new Logarithm(arr[j], base, logEpsilon);
            System.out.println("Logarithm-base-("+ base +")(" + arr[j] + ") = " + tmp.logarithmRecursive());
        }





    }




}
