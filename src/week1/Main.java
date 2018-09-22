package week1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {




    public static void main(String[] args){
        // Measurements (1. number of Fibonacci numbers // 2. time)
        HashMap simpleMap = new HashMap();
        HashMap memoMap = new HashMap();

    // Rerun each method with different size

        // specify maximum number of fibonumbers
        for(int j = 10; j <= 35; j=j+5) {

        // Simple
            System.out.println("\nSimple");
            int[] tmpSimpleArray = new int[j];

                long simpleStart = System.nanoTime();
                for(int i = 0; i < j; i++) {
                    FibonacciSimple tempF = new FibonacciSimple(i);
                    tmpSimpleArray[i] = (int) tempF.divideAndConquer();
                }
                long simpleEnd = System.nanoTime();

                for(int i = 0; i < j; i++) {
                    System.out.print(" " + tmpSimpleArray[i]);
                }

                // Add measurements to simpleMap
                simpleMap.put(j, simpleEnd-simpleStart);

        // Memo
                System.out.println("\nMemo");
                int[] tmpMemoArray = new int[j];

                long memoStart = System.nanoTime();
                for(int i = 0; i < j; i++) {
                    FibonacciMemo tempF = new FibonacciMemo(i);
                    tmpMemoArray[i] = (int) tempF.divideAndConquer(tempF.getHashMap());

                    // Check if Hashmap of memoFibonacci contains something     !!!! Delete this line after everything works!!!!!!!!!
                    System.out.println("\n" + tempF.getHashMap().toString());

                }
                long memoEnd = System.nanoTime();

                for(int i = 0; i < j; i++) {
                    System.out.print(" " + tmpMemoArray[i]);
                }




            // Add measurements to memoMap
                memoMap.put(j, memoEnd-memoStart);


        }
        // Print out measurements
            // Simple
            System.out.println("\n" + simpleMap.toString());

            // Memo
            System.out.println("\n" + memoMap.toString());









    }




}
