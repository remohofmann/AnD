package week1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import week1.exercices.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FibonacciThreadsTest {

    @Test
    public void calculateFibonacciNumbers() throws InterruptedException {
        //TODO: compare computational time between normal and with threads
        int fibo = 5;
        int maxThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        FibonacciThreads<Integer> fiboThreads = new FibonacciThreads<Integer>(fibo, (ThreadPoolExecutor) executorService, maxThreads);
        System.out.println("Starting threads...");
        System.out.println(fibo +" = "+ fiboThreads.divideAndConquer((ThreadPoolExecutor) executorService));
        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.MINUTES);
        do { // nothing
        } while (!executorService.isTerminated());
        System.out.println("All threads finished");

    }


}