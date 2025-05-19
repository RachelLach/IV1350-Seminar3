package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.observers.TotalRevenueObserver;
import se.kth.iv1350.pos.util.FileLogger;


public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private FileLogger logger;

    public TotalRevenueFileOutput(String filename) {
        logger = new FileLogger(filename);
    }

    @Override
    public void updateTotalRevenue(double totalRevenue) {
        String message = "Total revenue so far: " + String.format("%.2f", totalRevenue) + " SEK";
        logger.log(message);
    }
}
