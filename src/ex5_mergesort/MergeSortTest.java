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

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();
        IntegerComparator sorter = new IntegerComparator();
        int max = 200;              // numbers of arrays
        int increase = 2;        // minimal array size
        int maxThreads = 2;     // = number of cores
        int averaging = 10;

        for (int j = 1; j < max + 1; j++) {

            int arraySize = j * increase;
            Integer[] dataArraySimple = new Integer[arraySize];
            Integer[] auxArraySimple = new Integer[arraySize];

            Integer[] dataArrayThreads = new Integer[arraySize];
            Integer[] auxArrayThreads = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++) {
                dataArraySimple[i] = random.nextInt(5 * arraySize);
                dataArrayThreads[i] = random.nextInt(5 * arraySize);
            }

            // ***************************** SIMPLE ********************************************
            MergeSortIntegerSimple mergeSortIntegerSimple = new MergeSortIntegerSimple(dataArraySimple,
                    auxArraySimple, 0, dataArraySimple.length - 1);
            System.out.println("SIMPLE Array Size = " + arraySize);
            System.out.println("Before: " + mergeSortIntegerSimple);

            long simpleStart = 0;
            long durationSimple = 0;
            long durationSimpleAverage = 0;
            for (int i = 0; i < averaging; i++) {
                simpleStart = System.nanoTime();
                mergeSortIntegerSimple.divideAndConquer();
                durationSimple = System.nanoTime() - simpleStart;
                durationSimpleAverage = (durationSimpleAverage + durationSimple) / (i + 1);
            }
            simpleMap.put(arraySize, durationSimple);
            System.out.println("After: " + mergeSortIntegerSimple);
            System.out.println();

            // ***************************** THREADS ********************************************
            long threadsStart = 0;
            long threadsDuration = 0;
            long durationThreadsAverage = 0;

            ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
            MergeSortIntegerThreads mergeSortIntegerThreads = new MergeSortIntegerThreads(dataArrayThreads,
                    auxArrayThreads, 0, dataArrayThreads.length - 1, (ThreadPoolExecutor) executorService);

            System.out.println("THREADS Array Size = " + arraySize);
            System.out.println("Before: " + mergeSortIntegerThreads);

            for (int i = 0; i < averaging; i++) {
                threadsStart = System.nanoTime();
                mergeSortIntegerThreads.divideAndConquer((ThreadPoolExecutor) executorService);
                threadsDuration = System.nanoTime() - threadsStart;
                durationThreadsAverage = (durationThreadsAverage + threadsDuration) / (i + 1);
                executorService.shutdown();
            }

            threadsMap.put(arraySize, durationThreadsAverage);
            System.out.println("After: " + mergeSortIntegerThreads);
            System.out.println();
        }


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            VisualizeData visualizeData = new VisualizeData(simpleMap, threadsMap);
            visualizeData.showData(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
