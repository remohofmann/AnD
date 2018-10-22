package ex5_mergesort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static javafx.application.Application.launch;

public class MergeInsertionSortTest extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap threadsMap = new HashMap();
    private static HashMap threadsInsertionMap = new HashMap();

    private static int ARRAYSIZE =  Integer.valueOf(10000);  //size of arrays to compare
    private static int averaging = 10;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();

        //int numberOfTests = 10;                 // number of tests per InsertionSort threshold
        int maxThreads = 4;                    // = number of cores
        int maxThreshold = 3200;                  // max length of InsertionSort subarray

        // set to true to print arrays to console -> time consuming!! remove for proper testing!
        boolean printToConsole = false;

        for (int j = 1; j < maxThreshold + 1; j++) {
            //construction of the number arrays
            Integer[] dataArrayThreadsInsertion = new Integer[ARRAYSIZE];
            Integer[] auxArrayThreadsInsertion = new Integer[ARRAYSIZE];

            //AVERAGE CASE
            //for (int i = 0; i < ARRAYSIZE; i++) {
            //    dataArrayThreadsInsertion[i] = random.nextInt(5 * ARRAYSIZE);
            //}

            //WORST CASE
            for (int i = ARRAYSIZE -1; i >=0 ; i--) {
                dataArrayThreadsInsertion[ARRAYSIZE - i - 1] = i;
            }

            // ***************************** THREADS WITH INSERTION SORT **************************
            ExecutorService executorServiceInsertion = Executors.newFixedThreadPool(maxThreads);
            MergeSortIntegerThreadsInsertionSort mergeSortIntegerThreadsInsertionSort = new MergeSortIntegerThreadsInsertionSort(dataArrayThreadsInsertion,
                    auxArrayThreadsInsertion, 0, dataArrayThreadsInsertion.length - 1, (ThreadPoolExecutor) executorServiceInsertion, j);

            if (printToConsole) {
                System.out.println("THREADS WITH INSERTION SORT Array Size = " + ARRAYSIZE);
                System.out.println("Before: " + mergeSortIntegerThreadsInsertionSort);
            }

            long threadsStartInsertion;
            long threadsDurationInsertion;
            long threadsDurationInsertionAverage = 0;
            for (int i = 0; i < averaging; i++) {
                threadsStartInsertion = System.nanoTime();
                mergeSortIntegerThreadsInsertionSort.divideAndConquer((ThreadPoolExecutor) executorServiceInsertion);
                threadsDurationInsertion = System.nanoTime() - threadsStartInsertion;
                threadsDurationInsertionAverage = (threadsDurationInsertionAverage + threadsDurationInsertion);
            }
            threadsDurationInsertionAverage = threadsDurationInsertionAverage / averaging;

            executorServiceInsertion.shutdown();
            threadsInsertionMap.put(j, threadsDurationInsertionAverage);

            if (printToConsole) {
                System.out.println("After: " + mergeSortIntegerThreadsInsertionSort);
                System.out.println();
            }
            System.out.println("Threshold = " + j);
            System.out.println(threadsDurationInsertionAverage  + "\n");
        }

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            // Show Data
            VisualizeThresholds visualizeData = new VisualizeThresholds(threadsInsertionMap);
            visualizeData.showData(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}