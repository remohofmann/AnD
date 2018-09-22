package week1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {




    public static void main(String[] args){
        // Measurements (1. number of Fibonacci numbers // 2. time)
        HashMap simpleMap = new HashMap();

    // Rerun each method with different size

        // specify maximum number of fibonumbers
        for(int j = 10; j <= 35; j=j+5) {

        // Simple
            System.out.println("Simple");
            int[] tmpSimpleArray = new int[j];

                long start = System.nanoTime();
                for(int i = 0; i < j; i++) {
                    FibonacciSimple tempF = new FibonacciSimple(i);
                    tmpSimpleArray[i] = (int) tempF.divideAndConquer();
                }
                long end = System.nanoTime();

                for(int i = 0; i < j; i++) {
                    System.out.print(" " + tmpSimpleArray[i]);
                }

                // Add measurements to simpleMap
                simpleMap.put(j, end-start);




        }
        // Print out measurements
            // Simple
            System.out.println();
            System.out.println(simpleMap.toString());










    }




}
