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
        int increase = 100;        // each time we'll increase the array size by 'multiple'
        for (int j = 1; j < max + 1; j++) {

            int arraySize = j * increase;
            Integer[] integers = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++) {
                integers[i] = random.nextInt(5 * arraySize);
            }

            IntegerMergeSort integerMergeSort = new IntegerMergeSort(integers);

//            System.out.println("Array Size = " + arraySize);
//            System.out.println("Before: " + integerMergeSort);

            long simpleStart = System.nanoTime();

            integerMergeSort.sort(sorter);

            long duration = System.nanoTime() - simpleStart;
            simpleMap.put(arraySize, duration);

//            System.out.println("After: " + integerMergeSort);
//            System.out.println();
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
