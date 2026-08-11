import yahoofinance.YahooFinance;
import yahoofinance.Stock;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Date;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        // The instruction asked for a Queue to store value and timestamp
        Queue<String> stockQueue = new LinkedList<>();
       
        // The symbol for the Dow Jones Industrial Average
        String symbol = "^DJI";

        System.out.println("--- Citi Real-Time Risk Monitor Started ---");
        System.out.println("Monitoring Symbol: " + symbol);
        System.out.println("Polling Interval: 5 Seconds\n");

        // The infinite loop to query data every 5 seconds
        while (true) {
            try {
                // 1. Query the data from Yahoo Finance
                Stock stock = YahooFinance.get(symbol);
                BigDecimal price = stock.getQuote().getPrice();
                Date timestamp = new Date();

                // 2. Format the data for the Queue
                String dataEntry = "Time: " + timestamp + " | Price: " + price;

                // 3. Store in the Queue
                stockQueue.add(dataEntry);

                // 4. Print the latest update to the console
                System.out.println("Stored in Queue: " + dataEntry);

            } catch (IOException e) {
                // Professional error handling: don't crash, just log the error and try again
                System.out.println("Error fetching data: " + e.getMessage());
            }

            try {
                // 5. Wait for 5 seconds (5000 milliseconds)
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Process interrupted.");
                break;
            }
        }
    }
}