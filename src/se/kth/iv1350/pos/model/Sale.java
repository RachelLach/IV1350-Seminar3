package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.integration.ItemDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * We Represent a sale transaction and managing items with calculating totals,
 * then retrieve the list of items in the sale.
 */

public class Sale {
    private final List<Item> items;

    public Sale() {
        items = new ArrayList<>();
    }

    public void addItem(ItemDTO itemDTO) {
        for (Item item : items) {
            if (item.getItemInfo().getItemID().equals(itemDTO.getItemID())) {
                item.increaseQuantity();
                return;
            }
        }

        items.add(new Item(itemDTO));
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getItemInfo().getPrice() * item.getQuantity();
        }
        return total;
    }


    public double getTotalVAT() {
        double vat = 0;
        for (Item item : items) {
            vat += item.getItemInfo().getPrice() * item.getQuantity() * item.getItemInfo().getVatRate();
        }
        return vat;
    }

    public List<Item> getItems() {
        return items;
    }
}
