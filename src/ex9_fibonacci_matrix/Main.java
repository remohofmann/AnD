package ex9_fibonacci_matrix;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    private static HashMap matrixMap = new HashMap();
    private static HashMap memoMap = new HashMap();
    private static HashMap linearMap = new HashMap();
    private static HashMap logMap = new HashMap();
    private static HashMap quadraticMap = new HashMap();
    private static HashMap cubicMap = new HashMap();

    private static int averaging = 10;

    public static void main(String[] args) {
        // define numbers of fibonumbers, for 'long' we might not exceed F(92)!
        int n = 93;
        long[] fibonacciValues = new long[n];
        long matrixStart, matrixDuration, matrixDurationSum;

        for (int j = 0; j < n; j++) {
            FibonacciMatrix baseMatrix = new FibonacciMatrix(new long[]{0, 1}, new long[]{1, 1});

            matrixDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                matrixStart = System.nanoTime();
                fibonacciValues[j] = baseMatrix.toPower(j).getLine1()[1];
                matrixDuration = System.nanoTime() - matrixStart;
                matrixDurationSum = matrixDurationSum + matrixDuration;
            }
            // Add measurements to matrixMap
            matrixMap.put(j, matrixDurationSum / averaging);
        }
        for (int j = 0; j < n; j++) {
            System.out.println("F(" + j + ") = " + fibonacciValues[j]);
        }


       // OLD CODE END **********************

        // compute values for comparing functions (log, linear, ...)
        for (int j = 1; j <= n; j++) {
            // linear
            linearMap.put(j, j*1000);
            // log
            logMap.put(j, Math.log(j)*1000);
            // quadratic
            quadraticMap.put(j, (j * j)*10);
            // cubic
            cubicMap.put(j, (j * j * j)*10);
        }

        // Print out measurements
        // Simple
        System.out.println();
        System.out.println("MatrixMap times");
        System.out.println(matrixMap.toString());
        System.out.println();

        // linear
        System.out.println("Linear");
        System.out.println(linearMap.toString());
        System.out.println();
        // log
        System.out.println("Log");
        System.out.println(logMap.toString());
        System.out.println();
        // quadratic
        System.out.println("Quadratic");
        System.out.println(quadraticMap.toString());
        System.out.println();
        // cubic
        System.out.println("Cubic");
        System.out.println(cubicMap.toString());
        System.out.println();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            ViewDataController viewDataController = new ViewDataController(matrixMap, linearMap, logMap, quadraticMap, cubicMap);
            viewDataController.showData(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




