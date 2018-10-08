package week1;

import org.junit.Test;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FibonacciThreadsTest {

    @Test
    public void FibonacciComparedTest() throws ExecutionException, InterruptedException {
        int s = 10;
        int l = 35;
        System.out.println("Fibonacci " + s + ",...," + l);
        int maxThreads = 100;
        System.out.println("Max threads:  " + maxThreads);
        System.out.println();
        /** SIMPLE **********************************************************/
        System.out.println("Fibonacci Simple");
        long simpleStart = System.currentTimeMillis();
        for (int i = s; i < l; i++) {
            System.out.print("f(" + i + ")=" + new FibonacciSimple<Integer>(i).divideAndConquer());
            System.out.print(" ");
        }
        long simpleEnd = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time: " + (simpleEnd - simpleStart) + " ms");

        /** MEMO **********************************************************/
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

        /** THREADS **********************************************************/
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
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
        executorService.shutdown();
    }


}