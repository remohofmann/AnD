package ex6_quicksort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class QuickSortTest extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap threadsMap = new HashMap();

    private static int averaging = 10;
    private static int maxThreads = 2;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();

        int numberOfTests = 100;              // number of tests
        int initialArraySize = 100;        // increase Arraysize with this value
        int increaseArraySize = 100;

        // set to true to print arrays to console -> time consuming!! remove for proper testing!
        boolean printToConsole = false;

        for (int j = 0; j < numberOfTests; j++) {

            int arraySize = initialArraySize + j * increaseArraySize;
            Integer[] dataArraySimple = new Integer[arraySize];
            Integer[] dataArrayThreads = new Integer[arraySize];



            for (int i = 0; i < arraySize; i++) {
                dataArraySimple[i] = random.nextInt(5 * arraySize);
                dataArrayThreads[i] = random.nextInt(5 * arraySize);
            }

            // ***************************** SIMPLE ********************************************
            QuickSortSimple quickSortSimple = new QuickSortSimple(dataArraySimple, 0, dataArraySimple.length - 1);
            if (printToConsole) {
                System.out.println("SIMPLE Array Size = " + arraySize);
                System.out.println("Before: " + quickSortSimple);
            }
            long simpleStart = 0;
            long durationSimple = 0;
            long durationSimpleAverage = 0;
            for (int i = 0; i < averaging; i++) {
                simpleStart = System.nanoTime();
                quickSortSimple.divideAndConquer();
                durationSimple = System.nanoTime() - simpleStart;
                durationSimpleAverage = (durationSimpleAverage + durationSimple) / (i + 1);
            }
            simpleMap.put(arraySize, durationSimpleAverage);
            if (printToConsole) {
                System.out.println("After: " + quickSortSimple);
                System.out.println();
            }

//            // ***************************** THREADS ********************************************
//            long threadsStart = 0;
//            long threadsDuration = 0;
//            long durationThreadsAverage = 0;
//
//            ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
//            MergeSortIntegerThreads mergeSortIntegerThreads = new MergeSortIntegerThreads(dataArrayThreads,
//                    auxArrayThreads, 0, dataArrayThreads.length - 1, (ThreadPoolExecutor) executorService);
//
//            if (printToConsole) {
//                System.out.println("THREADS Array Size = " + arraySize);
//                System.out.println("Before: " + mergeSortIntegerThreads);
//            }
//
//            for (int i = 0; i < averaging; i++) {
//                threadsStart = System.nanoTime();
//                mergeSortIntegerThreads.divideAndConquer((ThreadPoolExecutor) executorService);
//                threadsDuration = System.nanoTime() - threadsStart;
//                durationThreadsAverage = (durationThreadsAverage + threadsDuration) / (i + 1);
//            }
//            executorService.shutdown();
//
//            threadsMap.put(arraySize, durationThreadsAverage);
//            executorService.shutdown();
//
//            if (printToConsole) {
//                System.out.println("After: " + mergeSortIntegerThreads);
//                System.out.println();
//            }


        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Show Data
            VisualizeData visualizeData = new VisualizeData(simpleMap, threadsMap, averaging, maxThreads);
            visualizeData.showData(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
