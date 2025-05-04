package se.kth.iv1350.pos.controller;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.Receipt;

/**
 * Center of all other packages,
 * It coordinates the sale process from start to end.
 */
public class Controller {
    private Sale currentSale;
    private final ItemRegistry itemRegistry;

    public Controller(ItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
    }

    public double getTotal() {
        return currentSale.getTotal();
    }

    public void startSale() {
        currentSale = new Sale();
    }

    public String addItem(String itemID) {
        ItemDTO item = itemRegistry.findItem(itemID);
        if (item == null) {
            return "Item not found.";
        }
        currentSale.addItem(item);
        return item.getName() + ": " + item.getPrice();
    }

    public double getVatTotal() {
        return currentSale.getTotalVAT();
    }

    public String endSale() {
        Receipt receipt = new Receipt(currentSale);
        return receipt.toString();
    }
}
