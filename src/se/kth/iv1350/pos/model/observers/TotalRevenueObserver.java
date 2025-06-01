package se.kth.iv1350.pos.model.observers;

/**
 * Observer interface for classes interested in total revenue updates.
 * Classes implementing this interface will receive notifications when
 * the total revenue of all sales changes.
 */
public interface TotalRevenueObserver {
    /**
     * Method called to notify the observer of the new total revenue.
     * @param totalRevenue the updated total revenue amount
     */
    void updateTotalRevenue(double totalRevenue);
}

