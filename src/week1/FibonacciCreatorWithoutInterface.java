package week1;

import java.util.ArrayList;
import java.util.List;

public class FibonacciCreatorWithoutInterface {
    // Fibonacci Sequences Storage
    private static int[] memoList = new int[50];
    private static List<Integer> threadList = new ArrayList<>();


    // Fibonacci Functions
    public static int makeFiboSimple(int n) {
        if(n <= 1) return n;
        return makeFiboSimple(n-1) + makeFiboSimple(n-2);
    }

    public static int makeFiboMemo(int n) {
        if(n <= 1) return n;
        if(memoList[n] > 0) return memoList[n];

        // If entry does not exist
        memoList[n] = makeFiboMemo(n-1) + makeFiboMemo(n-2);
        return makeFiboMemo(n-1) + makeFiboMemo(n-2);
    }

    public static int makeFiboThreads(int n) {
        if(n <= 1) return n;
        return makeFiboThreads(n-1) + makeFiboThreads(n-2);
    }





    public static void main(String[] args){

        // Simple
        System.out.println("Simple");
        for(int i = 0; i < 20; i++) {
            System.out.print(makeFiboSimple(i) + " ");
        }

        // Memoization
        System.out.println("Memoization");
        for(int i = 0; i < 20; i++) {
            System.out.print(makeFiboMemo(i) + " ");
        }

        // Check if memoList is not empty
        System.out.println("Check if memoList is not empty");

        int sumMemoList = 0;
        for(int i = 0; i < memoList.length; i++){
            sumMemoList = sumMemoList + memoList[i];
        }
        System.out.println("Summe memoList: " + sumMemoList);

    }













}
