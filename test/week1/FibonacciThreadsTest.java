package week1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.exercices.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class FibonacciThreadsTest {

    private ExecutorService executorService;

    /*@Test
    public void calculateFibonacciNumbers() throws InterruptedException {
        //TODO: compare computational time between normal and with threads
        int fibo = 10;
        int maxThreads = 10;
        executorService = Executors.newFixedThreadPool(maxThreads);
        FibonacciThreads<Integer> fiboThreads = new FibonacciThreads<Integer>(fibo, (ThreadPoolExecutor) executorService);
        System.out.println("Starting threads...");
        System.out.println(fibo +" = "+ fiboThreads.divideAndConquer((ThreadPoolExecutor) executorService));
//        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.MINUTES);
        do { // nothing
        } while (!executorService.isTerminated());
        System.out.println("All threads finished");

    }*/

    @Test
    public void FibonacciComparedTest() throws ExecutionException, InterruptedException {
        int s = 10;
        int l = 35;
        int maxThreads = 10;
        executorService = Executors.newFixedThreadPool(maxThreads);
        System.out.println("Fibonacci " + s + ",...," + l);
        System.out.println("Fibonacci Simple");
        long simpleStart = System.currentTimeMillis();
        for (int i = s; i < l; i++) {
            System.out.print("f(" + i + ")=" + new FibonacciSimple<Integer>(i).divideAndConquer());
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
            System.out.print("f(" + i + ")=" + new FibonacciMemo<Integer>(i).divideAndConquer(fibValMap));
            System.out.print(" ");
        }
        simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time: " + (simpleEnd - simpleStart) +" ms");


        System.out.println();
        System.out.println("Fibonacci with Threads");
        simpleStart = System.currentTimeMillis();
        for (int i = s; i < l; i++) {
            System.out.print("f(" + i + ")=" + new FibonacciThreads<Integer>(i, (ThreadPoolExecutor) executorService).divideAndConquer((ThreadPoolExecutor) executorService));
            System.out.print(" ");
        }
        simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time: " + (simpleEnd - simpleStart) +" ms");
    }


}