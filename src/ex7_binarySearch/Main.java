package ex7_binarySearch;

public class Main {

    public static void main(String[] args){
        int[] arr = new int[]{1, 4, 9, 16, 25, 4500};
        double epsilon = 0.001;

        System.out.println("---- SQRT----");
        System.out.println("Epsilon: " + epsilon);
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
        double base = 2;
        double logEpsilon = 0.001;
        arr = new int[]{1, 2, 4, 8, 9, 16, 32, 64, 567, 1024};
        System.out.println();
        System.out.println("---- LOGARITHM----");
        System.out.println("Epsilon: " + logEpsilon);
        System.out.println("----Iterative Function----");
        Logarithm tmp;
        for (int j = 0; j < arr.length; j++) {
            tmp = new Logarithm(arr[j], base, logEpsilon);
            System.out.println("log-base-"+base+"-(" + arr[j] + ") = " + tmp.logarithmIterative());
        }
        System.out.println();
        System.out.println("---- Recursive Function----");
        for (int j = 0; j < arr.length; j++) {
            tmp = new Logarithm(arr[j], base, logEpsilon);
            System.out.println("log-base-"+ base +"-(" + arr[j] + ") = " + tmp.logarithmRecursive());
        }





    }




}
