package ex_7_binarySearch;

public class Main {

    public static void main(String[] args){
        int arr[] = new int[]{1, 4, 9, 16, 25};
        double epsilon = 0.001;

        System.out.println("----Iterative Function----");
        System.out.println("Epsilon: " + epsilon);
        for(int j = 0; j<arr.length; j++) {
            SquareRoot tmp = new SquareRoot(arr[j], epsilon);
            System.out.println("x = " + arr[j] + "; sgrt(x) = " + tmp.squareRootIterative());
        }





    }




}
