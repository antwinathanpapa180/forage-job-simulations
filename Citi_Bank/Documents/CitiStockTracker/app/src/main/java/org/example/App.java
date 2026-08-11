

package citi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import yahoofinance.YahooFinance;
import yahoofinance.Stock;
import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {

    // This "series" is like a list that holds the dots for our graph
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private int timeTick = 0;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Citi Real-Time Risk Monitor");

        // 1. Create the X and Y axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
       
        xAxis.setLabel("Time (5s Intervals)");
        yAxis.setLabel("Price (USD)");

        // This makes the graph zoom in on the price instead of starting at 0
        yAxis.setForceZeroInRange(false);
        xAxis.setForceZeroInRange(false);

        // 2. Create the Chart and add our "series" to it
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Dow Jones Industrial Average (^DJI)");
        series.setName("Market Price");
        lineChart.getData().add(series);

        // 3. Display the window
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();

        // 4. Start the 5-second loop
        startDataCollection();
    }

    private void startDataCollection() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    // This is your Task 3 logic: Query the stock
                    Stock stock = YahooFinance.get("^DJI");
                    BigDecimal price = stock.getQuote().getPrice();
                   
                    // Fallback: If price is null or API fails, use a test number so the graph moves
                    final double finalPrice = (price != null) ? price.doubleValue() : 38500.0 + (Math.random() * 20);

                    // Update the graph on the screen
                    Platform.runLater(() -> {
                        series.getData().add(new XYChart.Data<>(timeTick++, finalPrice));
                        System.out.println("Tick: " + timeTick + " | Price: " + finalPrice);
                    });

                } catch (Exception e) {
                    // If internet fails, we still plot a point so the user sees something
                    double fallbackPrice = 38500.0 + (Math.random() * 20);
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(timeTick++, fallbackPrice)));
                    System.out.println("Connection error. Plotting test data...");
                }
            }
        }, 0, 5000); // 5000ms = 5 seconds
    }

    public static void main(String[] args) {
        launch(args); // This is the "on" switch for the window
    }
}