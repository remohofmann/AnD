package ex5_mergesort;

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
    private HashMap threadsMap;
    private HashMap threadsInsertionMap;
    private int boundary;


    public VisualizeData(HashMap simpleMap, HashMap threadsMap, HashMap threadsInsertionMap, int boundary) {
        this.simpleMap = simpleMap;
        this.threadsMap = threadsMap;
        this.threadsInsertionMap = threadsInsertionMap;
    }

    public VisualizeData(HashMap simpleMap) {
        this.simpleMap = simpleMap;
    }

    public Stage showData(Stage primaryStage) {
        // Sort Maps
        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(this.simpleMap);
        TreeMap<Integer, Integer> threadsTreeMap = new TreeMap<Integer, Integer>(this.threadsMap);
        TreeMap<Integer, Integer> threadsInsertionTreeMap = new TreeMap<Integer, Integer>(this.threadsInsertionMap);


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

        XYChart.Series threadsSeries = new XYChart.Series();
        Set<Integer> memoKeys = threadsTreeMap.keySet();
        for (Integer key : memoKeys) {
            threadsSeries.getData().add(new XYChart.Data(key, this.threadsMap.get(key)));
        }

        XYChart.Series threadInsertionSeries = new XYChart.Series();
        Set<Integer> threadInsertionKeys = threadsInsertionTreeMap.keySet();
        for (Integer key : threadInsertionKeys) {
            threadInsertionSeries.getData().add(new XYChart.Data(key, this.threadsInsertionMap.get(key)));
        }
        simpleSeries.setName("Simple");
        threadsSeries.setName("Threads");
        threadInsertionSeries.setName("Threads/Insertion (Boundary =" + this.boundary + ")");

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(simpleSeries);
        lineChart.getData().add(threadsSeries);
        lineChart.getData().add(threadInsertionSeries);

        primaryStage.setScene(scene);
        return primaryStage;
    }



}


