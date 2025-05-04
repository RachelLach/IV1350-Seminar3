package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.integration.ItemDTO;

/**
 * Presents a single item and item in the sale and manage item quantity
 * */
public class Item {
    private final ItemDTO itemInfo;
    private int quantity;

    public Item(ItemDTO itemInfo) {
        this.itemInfo = itemInfo;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemDTO getItemInfo() {
        return itemInfo;
    }
}
