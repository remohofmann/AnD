package ex5_mergesort;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;

public class MergeSortTest extends Application {

    private static HashMap simpleMap = new HashMap();

    public static void main(String[] args) {

        Random random = new Random();
        int max = 50;
        for (int j = 1; j < max + 1; j++) {

            int arraySize = j * 100;
            Integer[] integers = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++) {
                integers[i] = new Integer(random.nextInt(5 * arraySize));
            }

            MergeSort mergeSort = new MergeSort(integers);

//            System.out.println("Array Size = " + arraySize);
//            System.out.println("Before: " + mergeSort);

            long simpleStart = System.nanoTime();

            mergeSort.mergesortImpl(mergeSort.getDataArray(), mergeSort.getAuxArray(), 0, mergeSort.getDataArray().length - 1, new IntegerComparator());

            long duration = System.nanoTime() - simpleStart;
            simpleMap.put(arraySize, duration);

//            System.out.println("After: " + mergeSort);
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
