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
    private String[] functionNameList = {"Simple", "Memoization", "Matrix","Linear", "Log", "Quadratic", "Cubic"};



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
        System.out.println("Viewcontroller started");
        System.out.println(simpleMap);
        this.mapList = new ArrayList<HashMap>();

        // Make list of hashmaps
        this.mapList.add(simpleMap);
        this.mapList.add(memoMap);
        this.mapList.add(iterativeMap);
        this.mapList.add(linearMap);
        this.mapList.add(logMap);
        this.mapList.add(quadraticMap);
        this.mapList.add(cubicMap);

    }

    public void showData(Stage primaryStage) {
        Stage stage = primaryStage;
        stage.setTitle("Fibonacci Algorithms");

        // Axis Properties
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("F(n)");
        yAxis.setLabel("Computation Time");
        yAxis.setUpperBound(300000000);

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.setTitle("Fibonacci Data");

        // Add function points to chart
        for(int i = 0; i < mapList.size(); i++){
            TreeMap<Integer, Integer> tmpTreeMap = new TreeMap<Integer, Integer>(this.mapList.get(i));
            XYChart.Series tmpSeries = new XYChart.Series();
            Set<Integer> tmpKeys = tmpTreeMap.keySet();
            for (Integer key : tmpKeys) {
                tmpSeries.getData().add(new XYChart.Data(key, this.mapList.get(i).get(key)));
            }
            tmpSeries.setName(this.functionNameList[i]);
            lineChart.getData().add(tmpSeries);
        }

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.showData(primaryStage);
    }
}


