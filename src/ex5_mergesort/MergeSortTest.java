package ex5_mergesort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MergeSortTest extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap threadsMap = new HashMap();
    private static HashMap threadsInsertionMap = new HashMap();
    private static int boundary = 10; // boundary from where algorithm will switch to insertion sort
    private static int averaging = 10;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();

        int numberOfTests = 10;              // number of tests
        int initialArraySize = 3;        // increase Arraysize with this value
        int maxThreads = 4;     // = number of cores

        // set to true to print arrays to console -> time consuming!! remove for proper testing!
        boolean printToConsole = false;

        for (int j = 1; j < numberOfTests + 1; j++) {

            int arraySize = j * initialArraySize;
            Integer[] dataArraySimple = new Integer[arraySize];
            Integer[] auxArraySimple = new Integer[arraySize];

            Integer[] dataArrayThreads = new Integer[arraySize];
            Integer[] auxArrayThreads = new Integer[arraySize];

            Integer[] dataArrayThreadsInsertion = new Integer[arraySize];
            Integer[] auxArrayThreadsInsertion = new Integer[arraySize];

            for (int i = 0; i < arraySize; i++) {
                dataArraySimple[i] = random.nextInt(5 * arraySize);
                dataArrayThreads[i] = random.nextInt(5 * arraySize);
                dataArrayThreadsInsertion[i] = random.nextInt(5 * arraySize);
            }

            // ***************************** SIMPLE ********************************************
            MergeSortIntegerSimple mergeSortIntegerSimple = new MergeSortIntegerSimple(dataArraySimple,
                    auxArraySimple, 0, dataArraySimple.length - 1);
            if (printToConsole) {
                System.out.println("SIMPLE Array Size = " + arraySize);
                System.out.println("Before: " + mergeSortIntegerSimple);
            }
            long simpleStart = 0;
            long durationSimple = 0;
            long durationSimpleAverage = 0;
            for (int i = 0; i < averaging; i++) {
                simpleStart = System.nanoTime();
                mergeSortIntegerSimple.divideAndConquer();
                durationSimple = System.nanoTime() - simpleStart;
                durationSimpleAverage = (durationSimpleAverage + durationSimple) / (i + 1);
            }
            simpleMap.put(arraySize, durationSimpleAverage);
            if (printToConsole) {
                System.out.println("After: " + mergeSortIntegerSimple);
                System.out.println();
            }

            // ***************************** THREADS ********************************************
            long threadsStart = 0;
            long threadsDuration = 0;
            long durationThreadsAverage = 0;

            ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
            MergeSortIntegerThreads mergeSortIntegerThreads = new MergeSortIntegerThreads(dataArrayThreads,
                    auxArrayThreads, 0, dataArrayThreads.length - 1, (ThreadPoolExecutor) executorService);

            if (printToConsole) {
                System.out.println("THREADS Array Size = " + arraySize);
                System.out.println("Before: " + mergeSortIntegerThreads);
            }

            for (int i = 0; i < averaging; i++) {
                threadsStart = System.nanoTime();
                mergeSortIntegerThreads.divideAndConquer((ThreadPoolExecutor) executorService);
                threadsDuration = System.nanoTime() - threadsStart;
                durationThreadsAverage = (durationThreadsAverage + threadsDuration) / (i + 1);
            }
            executorService.shutdown();

            threadsMap.put(arraySize, durationThreadsAverage);
            executorService.shutdown();

            if (printToConsole) {
                System.out.println("After: " + mergeSortIntegerThreads);
                System.out.println();
            }

            // ***************************** THREADS WITH INSERTION SORT **************************
            ExecutorService executorServiceInsertion = Executors.newFixedThreadPool(maxThreads);
            MergeSortIntegerThreadsInsertionSort mergeSortIntegerThreadsInsertionSort = new MergeSortIntegerThreadsInsertionSort(dataArrayThreadsInsertion,
                    auxArrayThreadsInsertion, 0, dataArrayThreadsInsertion.length - 1, (ThreadPoolExecutor) executorServiceInsertion, boundary);

            if (printToConsole) {
                System.out.println("THREADS WITH INSERTION SORT Array Size = " + arraySize);
                System.out.println("Before: " + mergeSortIntegerThreadsInsertionSort);
            }

            long threadsStartInsertion;
            long threadsDurationInsertion;
            long threadsDurationInsertionAverage = 0;
            for (int i = 0; i < averaging; i++) {
                threadsStartInsertion = System.nanoTime();
                mergeSortIntegerThreadsInsertionSort.divideAndConquer((ThreadPoolExecutor) executorServiceInsertion);
                threadsDurationInsertion = System.nanoTime() - threadsStartInsertion;
                threadsDurationInsertionAverage = (threadsDurationInsertionAverage + threadsDurationInsertion) / (i + 1);
            }

            executorServiceInsertion.shutdown();
            threadsInsertionMap.put(arraySize, threadsDurationInsertionAverage);

            if (printToConsole) {
                System.out.println("After: " + mergeSortIntegerThreadsInsertionSort);
                System.out.println();
            }
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Show Data
            VisualizeData visualizeData = new VisualizeData(simpleMap, threadsMap, threadsInsertionMap, boundary, averaging);
            visualizeData.showData(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
