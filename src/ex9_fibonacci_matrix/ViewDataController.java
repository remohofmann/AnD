package ex9_fibonacci_matrix;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class ViewDataController extends Application {

    private HashMap simpleMap;
    private HashMap memoMap;
    private HashMap threadMap;

    public ViewDataController(HashMap simpleMap, HashMap memoMap, HashMap threadMap) {
        this.simpleMap = simpleMap;
        this.memoMap = memoMap;
        this.threadMap = memoMap;
    }

    public ViewDataController(HashMap simpleMap, HashMap memoMap) {
        this.simpleMap = simpleMap;
        this.memoMap = memoMap;
    }

    public void showData(Stage primaryStage) {
        Stage stage = primaryStage;

        // Sort Maps
        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(this.simpleMap);
        TreeMap<Integer, Integer> memoTreeMap = new TreeMap<Integer, Integer>(this.memoMap);

        stage.setTitle("Fibonacci Algorithms");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Fibonacci Data");
        xAxis.setLabel("Size of Fibonacci Line");
        yAxis.setLabel("Computation Time");


        XYChart.Series simpleSeries = new XYChart.Series();
        Set<Integer> simpleKeys = simpleTreeMap.keySet();
        for (Integer key : simpleKeys) {
            simpleSeries.getData().add(new XYChart.Data(key, this.simpleMap.get(key)));
        }

        XYChart.Series memoSeries = new XYChart.Series();
        Set<Integer> memoKeys = memoTreeMap.keySet();
        for (Integer key : memoKeys) {
            memoSeries.getData().add(new XYChart.Data(key, this.memoMap.get(key)));
        }

        simpleSeries.setName("Simple");
        memoSeries.setName("Memoization");

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(simpleSeries);
        lineChart.getData().add(memoSeries);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.showData(primaryStage);
    }
}


