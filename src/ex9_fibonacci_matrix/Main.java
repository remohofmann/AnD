package ex9_fibonacci_matrix;

import javafx.application.Application;
import javafx.stage.Stage;
import week1.FibonacciMemo;

import java.util.HashMap;

public class Main extends Application {

    private static HashMap simpleMap = new HashMap();
    private static HashMap memoMap = new HashMap();
    private static HashMap iterativeMap = new HashMap();
    private static HashMap linearMap = new HashMap();
    private static HashMap logMap = new HashMap();
    private static HashMap quadraticMap = new HashMap();
    private static HashMap cubicMap = new HashMap();

    public static void main(String[] args) {
        // define number of fibonumbers
        int n = 30;

    // OLD CODE **********************
        // Simple Fibonacci
        for (int j = 1; j <= n; j = j + 2) {

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

        // Memomoization Fibonacci
        for (int j = 1; j <= n; j = j + 2) {
            System.out.println("\nMemo");
            int[] tmpMemoArray = new int[j];

            long memoStart = System.nanoTime();
            for (int i = 0; i < j; i++) {
                FibonacciMemo tempF = new FibonacciMemo(i);
                tmpMemoArray[i] = (int) tempF.divideAndConquer(tempF.getHashMap());
            }
            long memoEnd = System.nanoTime();

            for (int i = 0; i < j; i++) {
                System.out.print(" " + tmpMemoArray[i]);
            }
                // Add measurements to memoMap
                memoMap.put(j, memoEnd - memoStart);

        }
        // OLD CODE END **********************

        // compute values for comparing functions (log, linear, ...)
        for (int j = 1; j <= n; j++){
            // linear
            linearMap.put(j, j);
            // log
            logMap.put(j, Math.log(j));
            // quadratic
            quadraticMap.put(j, j^2);
            // cubic
            cubicMap.put(j, j^3);
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

        // linear
        System.out.println("Linear");
        System.out.println(linearMap.toString());
        // log
        System.out.println("Log");
        System.out.println(logMap.toString());
        // quadratic
        System.out.println("Quadratic");
        System.out.println(quadraticMap.toString());
        // cubic
        System.out.println("cubic");
        System.out.println(cubicMap.toString());

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            ViewDataController viewDataController = new ViewDataController(simpleMap, memoMap, iterativeMap, linearMap,
                    logMap, quadraticMap, cubicMap);
            viewDataController.showData(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




