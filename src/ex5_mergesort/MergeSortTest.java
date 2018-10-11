package ex5_mergesort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;

public class MergeSortTest extends Application {

    private static HashMap simpleMap = new HashMap();

    public static void main(String[] args) {

        Random random = new Random();
        IntegerComparator sorter = new IntegerComparator();
        int max = 20;              // numbers of arrays
        int increase = 10;        // each time we'll increase the array size by 'multiple'
        for (int j = 1; j < max + 1; j++) {

            int arraySize = j * increase;
            Integer[] dataArray = new Integer[arraySize];
            Integer[] auxArray = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++) {
                dataArray[i] = random.nextInt(5 * arraySize);
            }

            MergeSortInteger mergeSortInteger = new MergeSortInteger(dataArray, auxArray, 0, dataArray.length-1);

            System.out.println("Array Size = " + arraySize);
            System.out.println("Before: " + mergeSortInteger);

            long simpleStart = System.nanoTime();

            mergeSortInteger.divideAndConquer();

            long duration = System.nanoTime() - simpleStart;
            simpleMap.put(arraySize, duration);

            System.out.println("After: " + mergeSortInteger);
            System.out.println();
        }
        // sort the simpleMap
//        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(simpleMap);
//        System.out.println(simpleTreeMap);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            VisualizeData visualizeData = new VisualizeData(simpleMap);
            visualizeData.showData(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
