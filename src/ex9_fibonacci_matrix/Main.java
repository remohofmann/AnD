package ex9_fibonacci_matrix;

import javafx.application.Application;
import javafx.stage.Stage;
import week1.FibonacciMemo;
import week1.FibonacciSimple;
import week1.FibonacciThreads;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main extends Application {

    private static HashMap matrixMap = new HashMap();
    private static HashMap matrixColMap = new HashMap();
    private static HashMap matrixOptMap = new HashMap();
    private static HashMap simpleMap = new HashMap();
    private static HashMap memoMap = new HashMap();
    private static HashMap threadsMap = new HashMap();
    private static HashMap linearMap = new HashMap();
    private static HashMap logMap = new HashMap();
    private static HashMap quadraticMap = new HashMap();
    private static HashMap cubicMap = new HashMap();

    private static int averaging = 10;
    private static boolean printToConsole = false;
    private static int maxThreads = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // define amount of fibonacci numbers to be calculated 0...n, for 'long' we might not exceed F(92)!
        int n = 30;
        long[] fibonacciValues = new long[n];
        long[] fibonacciValuesCol = new long[n];
        long[] fibonacciValuesOpt = new long[n];
        int[] fiboSimpleValues = new int[n];
        int[] fiboMemoValues = new int[n];
        int[] fiboThreadsValues = new int[n];
        long calcStart, calcDuration, calcDurationSum;

        // fibonacci calculation using a matrix made of lines
        for (int j = 0; j < n; j++) {
            FibonacciMatrix baseMatrix = new FibonacciMatrix(new long[]{0, 1}, new long[]{1, 1});
            calcDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                calcStart = System.nanoTime();
                fibonacciValues[j] = baseMatrix.toPower(j).getLine1()[1];
                calcDuration = System.nanoTime() - calcStart;
                calcDurationSum = calcDurationSum + calcDuration;
            }
            // Add measurements to matrixMap
            matrixMap.put(j, calcDurationSum / averaging);
        }
        // fibonacci calculation using a matrix made of columns
        for (int j = 0; j < n; j++) {
            FiboMatrixColumn baseMatrixCol = new FiboMatrixColumn(new long[]{0, 1}, new long[]{1, 1});
            calcDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                calcStart = System.nanoTime();
                fibonacciValuesCol[j] = baseMatrixCol.toPower(j).getColumn1()[0];
                calcDuration = System.nanoTime() - calcStart;
                calcDurationSum = calcDurationSum + calcDuration;
            }
            // Add measurements to matrixMap
            matrixColMap.put(j, calcDurationSum / averaging);
        }
        // fibonacci calculation using optimized matrix 
        for (int j = 0; j < n; j++) {
            FiboMatrixOptimized baseMatrixOpt = new FiboMatrixOptimized();
            calcDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                calcStart = System.nanoTime();
                fibonacciValuesOpt[j] = baseMatrixOpt.toPower(j).getFibo();
                calcDuration = System.nanoTime() - calcStart;
                calcDurationSum = calcDurationSum + calcDuration;
            }
            // Add measurements to matrixOptMap
            matrixOptMap.put(j, calcDurationSum / averaging);
        }

        // FibonacciSimple Calculation
        for (int j = 0; j < n; j++) {

            calcDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                week1.FibonacciSimple fiboSimple = new FibonacciSimple(j);
                calcStart = System.nanoTime();
                fiboSimpleValues[j] = (int) fiboSimple.divideAndConquer();
                calcDuration = System.nanoTime() - calcStart;
                calcDurationSum = calcDurationSum + calcDuration;
            }
            simpleMap.put(j, calcDurationSum / averaging);
        }


        // Fibonacci Memoization
        for (int j = 0; j < n; j++) {
            calcDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                FibonacciMemo fiboMemo = new FibonacciMemo(j);
                calcStart = System.nanoTime();
                fiboMemoValues[j] = (int) fiboMemo.divideAndConquer(fiboMemo.getHashMap());
                calcDuration = System.nanoTime() - calcStart;
                calcDurationSum = calcDurationSum + calcDuration;
            }
            memoMap.put(j, calcDurationSum / averaging);
        }

        // Fibonacci Threads
        for (int j = 0; j < n; j++) {
            calcDurationSum = 0;
            for (int i = 0; i < averaging; i++) {
                ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
                FibonacciThreads fiboThreads = new FibonacciThreads(j, (ThreadPoolExecutor) executorService);
                calcStart = System.nanoTime();
                fiboThreadsValues[j] = (int) fiboThreads.divideAndConquer((ThreadPoolExecutor) executorService);
                calcDuration = System.nanoTime() - calcStart;
                calcDurationSum = calcDurationSum + calcDuration;
                executorService.shutdown();
            }
            threadsMap.put(j, calcDurationSum / averaging);
        }


        if (false) {
            System.out.println("Fibonacci numbers calculation verification!");
            System.out.println("F(n) - lineMatrix \t F(n) - columnMatrix \t F(n) - optimized" +
                    "\t F(n) - simple \t F(n) - memo \t F(n) - threads");
            for (int j = 0; j < n; j++) {
                System.out.println("F(" + j + ") = " + fibonacciValues[j] +
                        "\t" + "F(" + j + ") = " + fibonacciValuesCol[j] +
                        "\t" + "F(" + j + ") = " + fibonacciValuesOpt[j] +
                        "\t" + "F(" + j + ") = " + fiboSimpleValues[j] +
                        "\t" + "F(" + j + ") = " + fiboMemoValues[j] +
                        "\t" + "F(" + j + ") = " + fiboThreadsValues[j]
                );
            }
        }
        // compute values for comparing functions (log, linear, ...)
        for (int j = 1; j <= n; j++) {
            // linear
            linearMap.put(j, j * 1000);
            // log
            logMap.put(j, Math.log(j) * 1000);
            // quadratic
            quadraticMap.put(j, (j * j) * 10);
            // cubic
            cubicMap.put(j, (j * j * j) * 10);
        }

        if (printToConsole) {
            // Print out measurements
            // Matrix
            System.out.println();
            System.out.println("Fibonacci Matrix Line times");
            System.out.println(matrixMap.toString());
            System.out.println();

            // Matrix column
            System.out.println();
            System.out.println("Fibonacci Matrix Column times");
            System.out.println(matrixMap.toString());
            System.out.println();

            // Matrix optimized
            System.out.println();
            System.out.println("Fibonacci Matrix optimized times");
            System.out.println(matrixOptMap.toString());
            System.out.println();

            // Matrix simple
            System.out.println();
            System.out.println("Fibonacci simple times");
            System.out.println(simpleMap.toString());
            System.out.println();

            // Matrix memo
            System.out.println();
            System.out.println("Fibonacci memo times");
            System.out.println(memoMap.toString());
            System.out.println();

            // Matrix threads
            System.out.println();
            System.out.println("Fibonacci threads times");
            System.out.println(threadsMap.toString());
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
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Show Data
            ViewDataController viewDataController = new ViewDataController(matrixMap, matrixColMap, matrixOptMap, simpleMap, memoMap, threadsMap, linearMap, logMap, quadraticMap, cubicMap);
            viewDataController.setupStage(primaryStage).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




