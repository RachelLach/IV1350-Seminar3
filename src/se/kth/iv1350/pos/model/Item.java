package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.integration.ItemDTO;

/**
 * Represents a single item in a sale, including its information and quantity.
 * This class keeps track of how many units of the item have been added to the sale.
 */
public class Item {
    private final ItemDTO itemInfo;
    private int quantity;

    /**
     * Creates a new instance of {@code Item} with the given item information and sets quantity to 1.
     * @param itemInfo The {@link ItemDTO} containing the details of the item.
     */
    public Item(ItemDTO itemInfo) {
        this.itemInfo = itemInfo;
        this.quantity = 1;
    }

    /**
     * Increases the quantity of this item by one.
     * This is typically called when the same item is added again to the sale.
     */
    public void increaseQuantity() {
        quantity++;
    }

    /**
     * Gets the quantity of this item in the current sale.
     * @return The number of units of the item.
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * Gets the item information associated with this sale item.
     * @return The {@link ItemDTO} containing the item's details (e.g., name, price, VAT).
     */
    public ItemDTO getItemInfo() {
        return itemInfo;
    }
}
