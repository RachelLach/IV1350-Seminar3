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
/**
 * The {@code Controller} class handles the main logic for the POS (Point of Sale) system.
 * It connects the view (user interface) with the model (business logic), and manages
 * the overall sale process: starting a sale, adding items, calculating totals, generating receipts,
 * and notifying observers about revenue updates.
 *This class helps keep the system organized by following the MVC pattern. It also
 * supports the Observer pattern by letting other parts of the program observe total revenue changes.
 */
public class Controller {
    private Sale currentSale;
    private final ItemRegistry itemRegistry;
    private final List<TotalRevenueObserver> revenueObservers = new ArrayList<>();
    private double totalRevenue = 0;

    /**
     * Creates a new instance of the controller.
     *
     * @param itemRegistry The registry that holds all the items in the system.
     */
    public Controller(ItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
    }

    /**
     * Adds an observer that will be notified whenever the total revenue is updated.
     *
     * @param observer The observer that will listen for revenue updates.
     */
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
