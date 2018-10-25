package ex6_quicksort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class QuickSortTest extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap threadsMap = new HashMap();

    private static int averaging = 10;
    private static int maxThreads = 2;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();

        int numberOfTests = 200;              // number of tests
        int initialArraySize = 1000;        // initial array size
        int increaseArraySize = 1000;       // increase Arraysize by this value

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

            // ***************************** QUICKSORT SIMPLE ********************************************
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
                durationSimpleAverage = durationSimpleAverage + durationSimple;
            }
            simpleMap.put(arraySize, durationSimpleAverage/averaging);
            if (printToConsole) {
                System.out.println("After: " + quickSortSimple);
                System.out.println();
            }

            // ***************************** QUICKSORT THREADS ********************************************
            long threadsStart = 0;
            long threadsDuration = 0;
            long durationThreadsAverage = 0;

            ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            QuickSortThreads quickSortThreads = new QuickSortThreads(dataArrayThreads,
                    0, dataArrayThreads.length - 1, threadPoolExecutor);

            if (printToConsole) {
                System.out.println("THREADS Array Size = " + arraySize);
                System.out.println("Before: " + quickSortThreads);
            }

            for (int i = 0; i < averaging; i++) {
                threadsStart = System.nanoTime();
                quickSortThreads.divideAndConquer(threadPoolExecutor);
                threadsDuration = System.nanoTime() - threadsStart;
                durationThreadsAverage = durationThreadsAverage + threadsDuration;
            }
            executorService.shutdown();
            threadPoolExecutor.shutdown();

            threadsMap.put(arraySize, durationThreadsAverage/averaging);

            if (printToConsole) {
                System.out.println("After: " + quickSortThreads);
                System.out.println();
            }


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
