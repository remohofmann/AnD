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
    private static int boundary = 10; // boundary from where algorithm will switch to insertionsort

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Random random = new Random();
        IntegerComparator sorter = new IntegerComparator();
        int numberOfTests = 30;              // number of tests
        int initialArraySize = 10;        // increase Arraysize with this value
        int maxThreads = 4;     // = number of cores

        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        ExecutorService executorServiceInsertion = Executors.newFixedThreadPool(maxThreads);

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
                    auxArrayThreads, 0, dataArrayThreads.length - 1, (ThreadPoolExecutor) executorService, boundary);

            System.out.println("THREADS Array Size = " + arraySize);
            System.out.println("Before: " + mergeSortIntegerThreads);
            long threadsStart = System.nanoTime();
            mergeSortIntegerThreads.divideAndConquer((ThreadPoolExecutor) executorService);
            long threadsDuration = System.nanoTime() - threadsStart;
            threadsMap.put(arraySize, threadsDuration);
            System.out.println("After: " + mergeSortIntegerThreads);
            System.out.println();

            // ***************************** THREADS WITH INSERTION SORT **************************
            MergeSortIntegerThreadsInsertionSort mergeSortIntegerThreadsInsertionSort = new MergeSortIntegerThreadsInsertionSort(dataArrayThreadsInsertion,
                    auxArrayThreadsInsertion, 0, dataArrayThreadsInsertion.length - 1, (ThreadPoolExecutor) executorServiceInsertion, boundary);

            System.out.println("THREADS WITH INSERTION SORT Array Size = " + arraySize);
            System.out.println("Before: " + mergeSortIntegerThreadsInsertionSort);
            long threadsStartInsertion = System.nanoTime();
            mergeSortIntegerThreadsInsertionSort.divideAndConquer((ThreadPoolExecutor) executorServiceInsertion);
            long threadsDurationInsertion = System.nanoTime() - threadsStartInsertion;
            threadsInsertionMap.put(arraySize, threadsDurationInsertion);
            System.out.println("After: " + mergeSortIntegerThreadsInsertionSort);
            System.out.println();

        }

        executorService.shutdown();
        executorServiceInsertion.shutdown();
        // sort the simpleMap
//        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(simpleMap);
//        System.out.println(simpleTreeMap);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Show Data
            VisualizeData visualizeData = new VisualizeData(simpleMap, threadsMap, threadsInsertionMap, boundary);
            visualizeData.showData(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
