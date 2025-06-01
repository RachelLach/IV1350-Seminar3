package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.integration.ItemDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale transaction in the POS system.
 * This class manages a list of items being purchased, calculates the total price and VAT,
 * and handles the addition of items to the sale.
 */
public class Sale {
    private final List<Item> items;

    /**
     * Creates a new instance of {@code Sale} with an empty list of items.
     */
    public Sale() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the current sale.
     * If the item has already been added previously, its quantity is increased by one.
     * Otherwise, the item is added as a new entry.
     * @param itemDTO The {@link ItemDTO} containing the item’s data (name, price, ID, VAT rate).
     */
    public void addItem(ItemDTO itemDTO) {
        for (Item item : items) {
            if (item.getItemInfo().getItemID().equals(itemDTO.getItemID())) {
                item.increaseQuantity();
                return;
            }
        }

        items.add(new Item(itemDTO));
    }

    /**
     * Finalizes the sale by performing any necessary calculations or state updates.
     * <p>This method can be used to lock the sale from further modifications,
     * calculate totals, VAT, discounts, or any other end-of-sale processing.
     * In the current implementation, this method may be empty if totals are
     * updated dynamically when items are added.</p>
     * <p>Calling this method signals that the sale is complete and ready for receipt generation.</p>
     */
    public void endSale() {

    }

    /**
     * Calculates the total cost of all items in the sale, excluding VAT.
     * @return The total amount to pay, not including VAT.
     */
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getItemInfo().getPrice() * item.getQuantity();
        }
        return total;
    }

    /**
     * Calculates the total VAT for all items in the sale.
     * @return The total VAT amount for the sale.
     */
    public double getTotalVAT() {
        double vat = 0;
        for (Item item : items) {
            vat += item.getItemInfo().getPrice() * item.getQuantity() * item.getItemInfo().getVatRate();
        }
        return vat;
    }

    /**
     * Gets the list of items included in the current sale.
     * @return A {@code List} of {@link Item} objects representing the items in the sale.
     */
    public List<Item> getItems() {
        return items;
    }
}
