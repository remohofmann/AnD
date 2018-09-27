package week1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FibonacciMemoTest {

    @Test
    public void FibonacciMemoComparedToFibonacciSimpleTest() {
        int s = 10;
        int l = 35;
        System.out.println("Fibonacci " + s + ",...," + l);
        System.out.println("Fibonacci Simple");
        long simpleStart = System.currentTimeMillis();
        for (int i = s; i < l; i++) {
            System.out.print("f(" + i + ")=" + new FibonacciSimple<Integer>(i).divideAndConquer().intValue());
            System.out.print(" ");
        }
        long simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time: " + (simpleEnd - simpleStart) + " ms");
        System.out.println();
        System.out.println("Fibonacci with Memo");
        simpleStart = System.currentTimeMillis();
        for (int i = s; i < l; i++) {
            HashMap fibValMap = new HashMap<>();
            System.out.print("f(" + i + ")=" + new FibonacciMemo<Integer>(i).divideAndConquer(fibValMap).intValue());
            System.out.print(" ");
        }
        simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time: " + (simpleEnd - simpleStart) +" ms");
    }
}