package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.observers.AbstractRevenueObserver;
import se.kth.iv1350.pos.util.FileLogger;

/**
 * Logs the total revenue to a file each time it is updated.
 * Extends AbstractRevenueObserver and uses FileLogger to persist revenue information.
 */
public class TotalRevenueFileOutput extends AbstractRevenueObserver {
    private final FileLogger logger;

    /**
     * Creates a new TotalRevenueFileOutput that logs revenue updates.
     * @param filename Not used here directly, since FileLogger handles the file.
     */
    public TotalRevenueFileOutput(String filename) {
        this.logger = FileLogger.getInstance();  // Singleton
    }

    @Override
    protected void doShowTotalRevenue(double totalRevenue) {
        String message = "Total revenue so far: " + String.format("%.2f", totalRevenue) + " SEK";
        logger.log(message);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Could not log revenue to file: " + e.getMessage());
    }
}
