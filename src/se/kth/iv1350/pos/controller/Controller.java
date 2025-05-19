package se.kth.iv1350.pos.controller;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pos.model.observers.TotalRevenueObserver;

public class Controller {
    private Sale currentSale;
    private final ItemRegistry itemRegistry;
    private final List<TotalRevenueObserver> revenueObservers = new ArrayList<>();
    private double totalRevenue = 0;

    public Controller(ItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
    }

    public void addRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    public void removeRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.remove(observer);
    }

    private void notifyRevenueObservers() {
        for (TotalRevenueObserver observer : revenueObservers) {
            observer.updateTotalRevenue(totalRevenue);
        }
    }

    public void startSale() {
        currentSale = new Sale();
    }

    public String addItem(String itemID) throws ItemNotFoundException, DatabaseException {
        ItemDTO item = itemRegistry.findItem(itemID);
        currentSale.addItem(item);
        return item.getName() + ": " + item.getPrice();
    }

    public double getTotal() {
        return currentSale.getTotal();
    }

    // ✅ This method was missing
    public double getVatTotal() {
        return currentSale.getTotalVAT();
    }

    public String endSale() {
        totalRevenue += currentSale.getTotal();
        notifyRevenueObservers();
        Receipt receipt = new Receipt(currentSale);
        return receipt.toString();
    }
}
