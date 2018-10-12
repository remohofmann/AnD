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
        int increase = 2;        // each time we'll increase the array size by 'multiple'
        int maxThreads = 2;     // = number of cores

        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);

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
            long simpleStart = System.nanoTime();
            mergeSortIntegerSimple.divideAndConquer();
            long duration = System.nanoTime() - simpleStart;
            simpleMap.put(arraySize, duration);
            System.out.println("After: " + mergeSortIntegerSimple);
            System.out.println();

            // ***************************** THREADS ********************************************
            MergeSortIntegerThreads mergeSortIntegerThreads = new MergeSortIntegerThreads(dataArrayThreads,
                    auxArrayThreads, 0, dataArrayThreads.length - 1, (ThreadPoolExecutor) executorService);

            System.out.println("THREADS Array Size = " + arraySize);
            System.out.println("Before: " + mergeSortIntegerThreads);
            long threadsStart = System.nanoTime();
            mergeSortIntegerThreads.divideAndConquer((ThreadPoolExecutor) executorService);
            long threadsDuration = System.nanoTime() - threadsStart;
            threadsMap.put(arraySize, threadsDuration);
            System.out.println("After: " + mergeSortIntegerThreads);
            System.out.println();
        }

        executorService.shutdown();
        // sort the simpleMap
//        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(simpleMap);
//        System.out.println(simpleTreeMap);

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
