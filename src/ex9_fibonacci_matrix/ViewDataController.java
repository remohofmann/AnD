package ex9_fibonacci_matrix;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.*;


public class ViewDataController extends Application {

    private List<HashMap> mapList;
    private String[] functionNameList = {"Matrix (lines)", "Matrix (columns)","Linear", "Log", "Quadratic", "Cubic"};



    public ViewDataController(HashMap matrixMap, HashMap matrixColMap, HashMap linearMap, HashMap logMap, HashMap quadraticMap, HashMap cubicMap) {
      /*  System.out.println("Viewcontroller started");
        System.out.println(simpleMap);*/

        this.mapList = new ArrayList<HashMap>();
        // Make list of hashmaps
        // ATTENTION: hashmaps have to be added in the same order as they are
        // listed in the 'functionNameList'!
        this.mapList.add(matrixMap);
        this.mapList.add(matrixColMap);
        this.mapList.add(linearMap);
        this.mapList.add(logMap);
        this.mapList.add(quadraticMap);
        this.mapList.add(cubicMap);

    }

    public void showData(Stage primaryStage) {
        // Stage properties
        Stage stage = primaryStage;
        stage.setTitle("Fibonacci");

        // Overall Axis Properties
        final NumberAxis xAxis = new NumberAxis();
        double upperBound = 40000;
        double tickUnit = 5000;
        final NumberAxis yAxis = new NumberAxis(0, upperBound, tickUnit);
        xAxis.setLabel("F(n)");
        yAxis.setLabel("Computation Time");

        // Chart properties
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.setTitle("Fibonacci Data");

        // Add function points to chart
        for(int i = 0; i < mapList.size(); i++){
            // convert HashMaps to TreeMaps
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


