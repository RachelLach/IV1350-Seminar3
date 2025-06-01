package se.kth.iv1350.pos.model.observers;

/**
 * Abstract base class using the Template Method pattern for revenue observers.
 * This class provides a fixed algorithm for updating total revenue and delegates
 * the display and error handling logic to subclasses.
 */
public abstract class AbstractRevenueObserver implements TotalRevenueObserver {
    @Override
    public final void updateTotalRevenue(double totalRevenue) {
        try {
            doShowTotalRevenue(totalRevenue);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Subclass-defined method for displaying revenue.
     * @param totalRevenue The updated total revenue.
     * @throws Exception If any error occurs when showing revenue.
     */
    protected abstract void doShowTotalRevenue(double totalRevenue) throws Exception;

    /**
     * Subclass-defined method for handling exceptions.
     * @param e The exception that occurred.
     */
    protected abstract void handleErrors(Exception e);
}
