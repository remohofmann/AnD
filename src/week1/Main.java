/*
package week1;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap memoMap = new HashMap();

    public static void main(String[] args) {
        // Measurements (1. number of Fibonacci numbers // 2. time)

        // Rerun each method with different size

        // specify maximum number of fibonumbers
        // Simple
        for (int j = 10; j <= 35; j = j + 5) {

            System.out.println();
            System.out.println("Simple");
            int[] tmpSimpleArray = new int[j];

            long simpleStart = System.nanoTime();
            for (int i = 0; i < j; i++) {
                FibonacciSimple tempF = new FibonacciSimple(i);
                tmpSimpleArray[i] = (int) tempF.divideAndConquer();
            }
            long simpleEnd = System.nanoTime();

            for (int i = 0; i < j; i++) {
                System.out.print(" " + tmpSimpleArray[i]);
            }

            // Add measurements to simpleMap
            simpleMap.put(j, simpleEnd - simpleStart);
        }


        // Memo
        for (int j = 10; j <= 35; j = j + 5) {
            System.out.println("\nMemo");
            int[] tmpMemoArray = new int[j];

            long memoStart = System.nanoTime();
            for (int i = 0; i < j; i++) {
                FibonacciMemo tempF = new FibonacciMemo(i);
                tmpMemoArray[i] = (int) tempF.divideAndConquer(tempF.getHashMap());

                // Check if Hashmap of memoFibonacci contains something     !!!! Delete this line after everything works!!!!!!!!!
//                System.out.println("\n" + tempF.getHashMap().toString());

            }
            long memoEnd = System.nanoTime();

            for (int i = 0; i < j; i++) {
                System.out.print(" " + tmpMemoArray[i]);
            }


            // Add measurements to memoMap
            memoMap.put(j, memoEnd - memoStart);


        }
        // Print out measurements
        // Simple
        System.out.println();
        System.out.println();
        System.out.println("SimpleMap times");
        System.out.println(simpleMap.toString());
        System.out.println();

        // Memo
        System.out.println("Memo times");
        System.out.println(memoMap.toString());


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            ViewDataController viewDataController = new ViewDataController(simpleMap, memoMap);
            viewDataController.showData(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




*/
