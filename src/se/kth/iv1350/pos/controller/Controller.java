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
import se.kth.iv1350.pos.util.Logger;
import se.kth.iv1350.pos.util.FileLogger;

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
    private final Logger logger;


    public Controller(ItemRegistry itemRegistry, Logger logger) {
        this.itemRegistry = itemRegistry;
        this.logger = logger;
    }

    public Controller(ItemRegistry itemRegistry) {
        this(itemRegistry, FileLogger.getInstance()); // delegate to main constructor
    }

    /**
     * Adds an observer that will be notified whenever the total revenue is updated.
     * @param observer The observer that will listen for revenue updates.
     */
    public void addRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * Removes a previously added revenue observer so it will no longer receive updates.
     * @param observer The observer to remove.
     */
    public void removeRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.remove(observer);
    }

    /**
     * Notifies all registered revenue observers about the current total revenue.
     */
    private void notifyRevenueObservers() {
        for (TotalRevenueObserver observer : revenueObservers) {
            observer.updateTotalRevenue(totalRevenue);
        }
    }

    /**
     * Starts a new sale by creating a new {@link Sale} instance.
     * This resets the current sale to begin adding items.
     */
    public void startSale() {
        currentSale = new Sale();
    }

    /**
     * Adds an item to the current sale based on the given item ID.
     * This method looks up the item in the item registry, adds it to the ongoing sale,
     * and returns the raw item data. It does not perform any formatting of the item information.
     * @param itemID The unique identifier of the item to be added.
     * @return The {@link ItemDTO} object representing the added item.
     * @throws ItemNotFoundException If the item ID does not exist in the item registry.
     * @throws DatabaseException If there is a problem accessing the item registry database.
     */
    public ItemDTO addItem(String itemID) throws ItemNotFoundException, DatabaseException {
        ItemDTO item = itemRegistry.findItem(itemID);
        currentSale.addItem(item);
        return item; // Return the raw item object, no formatting here
    }

    /**
     * Gets the total price of the current sale without VAT.
     * @return The total amount to pay excluding VAT.
     */
    public double getTotal() {
        return currentSale.getTotal();
    }

    /**
     * Gets the total price of the current sale without VAT.
     * @return The total amount to pay excluding VAT.
     */
    public double getVatTotal() {
        return currentSale.getTotalVAT();
    }

    /**
     * Ends the current sale, updating the total revenue and generating a receipt.
     * All registered observers are notified of the updated total revenue.
     * @return A string representation of the receipt for the sale.
     */
    public String endSale() {
        totalRevenue += currentSale.getTotal();
        notifyRevenueObservers();
        Receipt receipt = new Receipt(currentSale);
        return receipt.toString();
    }
}
