package ex6_quicksort;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class VisualizeData {

    private HashMap simpleMap;
    private HashMap threadsMap;
    private int  average;


    public VisualizeData(HashMap simpleMap, HashMap threadsMap, int average) {
        this.simpleMap = simpleMap;
        this.threadsMap = threadsMap;
        this.average = average;
    }


    public VisualizeData(HashMap simpleMap) {
        this.simpleMap = simpleMap;
    }

    public Stage showData(Stage primaryStage) {
        // Sort Maps
        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(this.simpleMap);
        TreeMap<Integer, Integer> threadsTreeMap = new TreeMap<Integer, Integer>(this.threadsMap);

        primaryStage.setTitle("Merge Sort Algorithms");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Merge Sort Data (avg: " + average + ")");
        xAxis.setLabel("Array Size");
        yAxis.setLabel("Computational Time [nano]");


        XYChart.Series simpleSeries = new XYChart.Series();
        Set<Integer> simpleKeys = simpleTreeMap.keySet();
        for (Integer key : simpleKeys) {
            simpleSeries.getData().add(new XYChart.Data(key, this.simpleMap.get(key)));
        }

        XYChart.Series threadsSeries = new XYChart.Series();
        Set<Integer> memoKeys = threadsTreeMap.keySet();
        for (Integer key : memoKeys) {
            threadsSeries.getData().add(new XYChart.Data(key, this.threadsMap.get(key)));
        }



        simpleSeries.setName("Simple");
        threadsSeries.setName("Threads");

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(simpleSeries);
        lineChart.getData().add(threadsSeries);

        primaryStage.setScene(scene);
        return primaryStage;
    }



}


