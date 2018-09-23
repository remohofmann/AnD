package week1;

        import java.util.*;

        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.chart.LineChart;
        import javafx.scene.chart.NumberAxis;
        import javafx.scene.chart.XYChart;
        import javafx.stage.Stage;


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
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);
        lineChart.setTitle("Fibonacci Data");
        xAxis.setLabel("Size of Fibonacci Line");
        yAxis.setLabel("Computation Time");


        XYChart.Series series1 = new XYChart.Series();
        Set<Integer> keys = simpleTreeMap.keySet();
        for(Integer key: keys){
            series1.getData().add(new XYChart.Data(key, this.simpleMap.get(key)));
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series1);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.showData(primaryStage);
    }
}


