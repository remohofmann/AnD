package ex9_fibonacci_matrix;

import ex6_quicksort.QuickSortSimple;
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

    private static int averaging = 10;

    public static void main(String[] args) {
        // define numbers of fibonumbers
        int n = 91;
        long[] fibonacciValues = new long[n];
        long simpleStart, simpleDuration, durationSimpleSum;

        System.out.println("Fibonacci with matrix calculation");
        for (int j = 0; j < n; j++) {
            FibonacciMatrix baseMatrix = new FibonacciMatrix(new long[]{0, 1}, new long[]{1, 1});

            durationSimpleSum = 0;
            for (int i = 0; i < averaging; i++) {
                simpleStart = System.nanoTime();
                fibonacciValues[j] = baseMatrix.toPower(j).getLine1()[1];
                simpleDuration = System.nanoTime() - simpleStart;
                durationSimpleSum = durationSimpleSum + simpleDuration;
            }
            // Add measurements to simpleMap
//            simpleMap.put(j, simpleDuration);
            simpleMap.put(j, durationSimpleSum / averaging);
//            System.out.println();
//            System.out.println("F(" + j + ") = " + fibonacciValues[j]);
        }


       /* // OLD CODE END **********************

        // compute values for comparing functions (log, linear, ...)
        for (int j = 1; j <= n; j++) {
            // linear
            linearMap.put(j, j);
            // log
            logMap.put(j, Math.log(j));
            // quadratic
            quadraticMap.put(j, j ^ 2);
            // cubic
            cubicMap.put(j, j ^ 3);
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
        System.out.println(cubicMap.toString());*/

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            ViewDataController viewDataController = new ViewDataController(simpleMap, memoMap, iterativeMap);
//            ViewDataController viewDataController = new ViewDataController(simpleMap, memoMap, iterativeMap, linearMap, logMap, quadraticMap, cubicMap);
            viewDataController.showData(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




