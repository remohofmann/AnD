package ex5_mergesort;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class VisualizeData  {

    private HashMap simpleMap;
    private HashMap memoMap;
    private HashMap threadMap;

    public VisualizeData(HashMap simpleMap, HashMap memoMap, HashMap threadMap) {
        this.simpleMap = simpleMap;
        this.memoMap = memoMap;
        this.threadMap = memoMap;
    }

    public VisualizeData(HashMap simpleMap, HashMap memoMap) {
        this.simpleMap = simpleMap;
        this.memoMap = memoMap;
    }

    public VisualizeData(HashMap simpleMap) {
        this.simpleMap = simpleMap;
    }

    public Stage showData(Stage primaryStage) {
        // Sort Maps
        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(this.simpleMap);
//        TreeMap<Integer, Integer> memoTreeMap = new TreeMap<Integer, Integer>(this.memoMap);

        primaryStage.setTitle("Merge Sort Algorithms");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Merge Sort Data");
        xAxis.setLabel("Array Size");
        yAxis.setLabel("Computational Time [nano]");


        XYChart.Series simpleSeries = new XYChart.Series();
        Set<Integer> simpleKeys = simpleTreeMap.keySet();
        for (Integer key : simpleKeys) {
            simpleSeries.getData().add(new XYChart.Data(key, this.simpleMap.get(key)));
        }

        /*XYChart.Series memoSeries = new XYChart.Series();
        Set<Integer> memoKeys = memoTreeMap.keySet();
        for (Integer key : memoKeys) {
            memoSeries.getData().add(new XYChart.Data(key, this.memoMap.get(key)));
        }*/

        simpleSeries.setName("Simple");
//        memoSeries.setName("Memoization");

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(simpleSeries);
//        lineChart.getData().add(memoSeries);

        primaryStage.setScene(scene);
        return primaryStage;
    }



}


