package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.observers.TotalRevenueObserver;




public class TotalRevenueView implements TotalRevenueObserver {

    @Override
    public void updateTotalRevenue(double totalRevenue) {
        System.out.println("Total revenue so far: " + String.format("%.2f", totalRevenue) + " SEK");
    }
}
