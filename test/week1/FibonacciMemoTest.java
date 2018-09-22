package week1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FibonacciMemoTest {

    private FibonacciMemo<Integer> fiboSimple0 = new FibonacciMemo<>(0);
    private FibonacciMemo<Integer> fiboSimple1 = new FibonacciMemo<Integer>(1);
    private FibonacciMemo<Integer> fiboSimple2 = new FibonacciMemo<Integer>(2);
    private FibonacciMemo<Integer> fiboSimple3 = new FibonacciMemo<Integer>(3);
    private FibonacciMemo<Integer> fiboSimple5 = new FibonacciMemo<Integer>(5);
    private FibonacciMemo<Integer> fiboSimple10 = new FibonacciMemo<Integer>(10);


    @Test
    public void calculateFibonacciNumbers() {
        int l = 35;
        System.out.println("Fibonacci 10,11,12,...,35");
        System.out.println("Fibonacci Simple");
        long simpleStart = System.currentTimeMillis();
        for (int i = 10; i < l; i++) {
            System.out.print("f(" + i + ")=" + new FibonacciSimple<Integer>(i).divideAndConquer().intValue());
            System.out.print(" ");
        }
        long simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time [milli seconds]: " + (simpleEnd - simpleStart));
        System.out.println();
        System.out.println("Fibonacci with Memo");
        simpleStart = System.currentTimeMillis();
        for (int i = 10; i < l; i++) {
            HashMap fibValMap = new HashMap<>();
            System.out.print("f(" + i + ")=" + new FibonacciMemo<Integer>(i).divideAndConquer(fibValMap).intValue());
            System.out.print(" ");
        }
        simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time [milli seconds]: " + (simpleEnd - simpleStart));

        // Measurements (1. number of Fibonacci numbers // 2. time)
        HashMap simpleMap = new HashMap();
        HashMap memoMap = new HashMap();

        // specify maximum number of fibonacci numbers
        // Simple
        /*for (int j = 10; j <= 35; j = j + 5) {

            System.out.println();
            System.out.println("Simple");
            int[] tmpSimpleArray = new int[j];

            simpleStart = System.nanoTime();
            for (int i = 0; i < j; i++) {
                FibonacciSimple tempF = new FibonacciSimple(i);
                tmpSimpleArray[i] = (int) tempF.divideAndConquer();
            }
            simpleEnd = System.nanoTime();

            for (int i = 0; i < j; i++) {
                System.out.print(i + "=" + tmpSimpleArray[i]);
                System.out.print(" ");
                System.out.print(" ");
            }
            // Add measurements to simpleMap
            simpleMap.put(j, simpleEnd - simpleStart);
        }


        // Memo
        for (int j = 10; j <= 35; j = j + 5) {
            System.out.println("\nMemo");
            HashMap reusableFibonacciNumbers = new HashMap();
            int[] tmpMemoArray = new int[j];

            long memoStart = System.nanoTime();
            for (int i = 0; i < j; i++) {
                FibonacciMemo tempF = new FibonacciMemo(i);
                tmpMemoArray[i] = (int) tempF.divideAndConquer(reusableFibonacciNumbers);
            }
            long memoEnd = System.nanoTime();

            for (int i = 0; i < j; i++) {
                System.out.print(i + "=" + tmpMemoArray[i]);
                System.out.print(" ");
                System.out.print(" ");
            }
            // Add measurements to memoMap
            memoMap.put(j, memoEnd - memoStart);
        }
        // Print out measurements
        // Simple
        System.out.println();
        System.out.println();
        System.out.println("SimpleMap times");
        System.out.println(simpleMap.toString());
        System.out.println();

        // Memo
        System.out.println("Memo times");
        System.out.println(memoMap.toString());*/


    }
}