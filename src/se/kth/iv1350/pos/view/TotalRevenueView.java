package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.observers.AbstractRevenueObserver;

/**
 * Displays the total revenue in the user interface (console output).
 * Inherits from AbstractRevenueObserver and provides specific behavior for
 * showing total revenue and handling display errors.
 */
public class TotalRevenueView extends AbstractRevenueObserver {

    /**
     * Shows the total revenue in the console.
     * @param totalRevenue The updated total revenue to be displayed.
     */
    @Override
    protected void doShowTotalRevenue(double totalRevenue) {
        System.out.println("Total revenue so far: " + String.format("%.2f", totalRevenue) + " SEK");
    }

    /**
     * Handles any exceptions that occur during display of revenue.
     * @param e The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Could not display total revenue: " + e.getMessage());
    }
}
