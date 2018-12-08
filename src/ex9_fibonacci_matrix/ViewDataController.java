package ex9_fibonacci_matrix;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.*;


public class ViewDataController extends Application {

    private HashMap simpleMap;
    private HashMap memoMap;
    private HashMap threadMap;
    private HashMap iterativeMap;
    private HashMap linearMap;
    private HashMap logMap;
    private HashMap quadraticMap;
    private HashMap cubicMap;
    private List<HashMap> mapList;


    public ViewDataController(HashMap simpleMap, HashMap memoMap, HashMap threadMap) {
        this.simpleMap = simpleMap;
        this.memoMap = memoMap;
        this.threadMap = threadMap;
    }

    public ViewDataController(HashMap simpleMap, HashMap memoMap, HashMap iterativeMap, HashMap linearMap, HashMap logMap, HashMap quadraticMap, HashMap cubicMap) {
        this.simpleMap = simpleMap;
        this.memoMap = memoMap;
        this.iterativeMap = iterativeMap;
        this.linearMap = linearMap;
        this.logMap = logMap;
        this.quadraticMap = quadraticMap;
        this.cubicMap = cubicMap;

// THIS CODELINE DOES NOT WORK!!!
        //Collections.addAll(this.mapList, simpleMap, memoMap, iterativeMap, linearMap, logMap, quadraticMap, cubicMap);
    }

    public void showData(Stage primaryStage) {
        Stage stage = primaryStage;

        // Sort Maps
        TreeMap<Integer, Integer> simpleTreeMap = new TreeMap<Integer, Integer>(this.simpleMap);
        TreeMap<Integer, Integer> memoTreeMap = new TreeMap<Integer, Integer>(this.memoMap);

        stage.setTitle("Fibonacci");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Fibonacci iterative matrix calculation");
        xAxis.setLabel("F(n)");
        yAxis.setLabel("Time");

        Scene scene = new Scene(lineChart, 800, 600);

// THIS CODELINES DOES NOT WORK!!!

//        for(int i = 0; i < mapList.size(); i++){
//            TreeMap<Integer, Integer> tmpTreeMap = new TreeMap<Integer, Integer>(this.mapList.get(i));
//            XYChart.Series tmpSeries = new XYChart.Series();
//            Set<Integer> tmpKeys = tmpTreeMap.keySet();
//            for (Integer key : tmpKeys) {
//                tmpSeries.getData().add(new XYChart.Data(key, this.mapList.get(i).get(key)));
//            }
//            tmpSeries.setName("Simple");
//            lineChart.getData().add(tmpSeries);
//
//        }

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

        simpleSeries.setName("Fibo Matrix");
        memoSeries.setName("Memoization");

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


