package ex5_mergesort;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class VisualizeThresholds {

    private HashMap thresholdValues;
    private int boundary;


    public VisualizeThresholds(HashMap thresholdValues) {
        this.thresholdValues = thresholdValues;
    }

    public Stage showData(Stage primaryStage) {
        // Sort Maps
        TreeMap<Integer, Integer> threshholdTreeMap = new TreeMap<Integer, Integer>(this.thresholdValues);


        primaryStage.setTitle("Threshhold comparison for Merge Sort > Insertion Sort");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("Threshold Value");
        yAxis.setLabel("Computational Time [nano]");

        XYChart.Series threadInsertionSeries = new XYChart.Series();
        Set<Integer> thresholdKeys = threshholdTreeMap.keySet();
        for (Integer key : thresholdKeys) {
            threadInsertionSeries.getData().add(new XYChart.Data(key, this.thresholdValues.get(key)));
        }

        threadInsertionSeries.setName("Threshhold numbers");

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(threadInsertionSeries);

        primaryStage.setScene(scene);
        return primaryStage;
    }



}


