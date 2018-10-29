package ex6_quicksort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap threadsMap = new HashMap();

    private static int averaging = 10;
    private static int maxThreads = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();

        int numberOfTests = 100;              // number of tests
        int initialArraySize = 1000;        // initial array size
        int increaseArraySize = 100;       // increase Arraysize by this value

        long simpleStart;
        long durationSimple;
        long durationSimpleSum;

        long threadsStart;
        long threadsDuration;
        long durationThreadsSum;

        // set to true to print arrays to console -> time consuming!! remove for proper testing!
        boolean printToConsole = false;
        // set to true to simulate worst case
        // setting to false will generate random values
        boolean generateWorstCase = true;

        for (int j = 0; j < numberOfTests; j++) {
            int arraySize = initialArraySize + j * increaseArraySize;
            Integer[] dataArraySimple = new Integer[arraySize];
            //WORST CASE
            for (int i = arraySize -1; i >=0 ; i--) {
                if (generateWorstCase) dataArraySimple[arraySize - i - 1] = i;
                else dataArraySimple[i] = random.nextInt(5 * arraySize);
            }

            // ***************************** QUICKSORT SIMPLE ********************************************
            QuickSortSimple quickSortSimple = new QuickSortSimple(dataArraySimple, 0, dataArraySimple.length - 1);
            if (printToConsole) {
                System.out.println("SIMPLE Array Size = " + arraySize);
                System.out.println("Before: " + quickSortSimple);
            }
            durationSimpleSum = 0;
            for (int i = 0; i < averaging; i++) {
                QuickSortSimple avgQuickSortSimple = quickSortSimple.copy();
                simpleStart = System.nanoTime();
                avgQuickSortSimple.divideAndConquer();
                durationSimple = System.nanoTime() - simpleStart;
                durationSimpleSum = durationSimpleSum + durationSimple;
            }
            simpleMap.put(arraySize, durationSimpleSum / averaging);
            if (printToConsole) {
                quickSortSimple.divideAndConquer();
                System.out.println("After: " + quickSortSimple);
                System.out.println();
            }

            // ***************************** QUICKSORT THREADS ********************************************

            Integer[] dataArrayThreads = new Integer[arraySize];
            //WORST CASE
            for (int i = arraySize -1; i >=0 ; i--) {
                if (generateWorstCase) dataArrayThreads[arraySize - i - 1] = i;
                else dataArrayThreads[i] = random.nextInt(5 * arraySize);
            }
            QuickSortThreads quickSortThreads = new QuickSortThreads(dataArrayThreads, 0, dataArrayThreads.length - 1);

            if (printToConsole) {
                System.out.println("THREADS Array Size = " + arraySize);
                System.out.println("Before: " + quickSortThreads);
            }
            durationThreadsSum = 0;
            for (int i = 0; i < averaging; i++) {
                // Copy the initial array, so that averaging makes sense!
                QuickSortThreads avgQuickSortThreads = quickSortThreads.copyWithoutExecutorService();

                ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
                avgQuickSortThreads.addThreadPoolExecutor((ThreadPoolExecutor) executorService);

                threadsStart = System.nanoTime();
                avgQuickSortThreads.divideAndConquer((ThreadPoolExecutor) executorService);
                threadsDuration = System.nanoTime() - threadsStart;

                executorService.shutdown();
                durationThreadsSum = durationThreadsSum + threadsDuration;
            }

            threadsMap.put(arraySize, durationThreadsSum / averaging);

            if (printToConsole) {
                ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
                quickSortThreads.addThreadPoolExecutor((ThreadPoolExecutor) executorService);
                quickSortThreads.divideAndConquer((ThreadPoolExecutor) executorService);
                executorService.shutdown();
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
