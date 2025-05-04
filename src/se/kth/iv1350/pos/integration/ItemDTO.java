package se.kth.iv1350.pos.integration;

/**
 * Presents an item and hold its data and
 * */
public class ItemDTO {
    private final String itemID;
    private final String name;
    private final double price;
    private final double vatRate;
    private final String description;

    public ItemDTO(String itemID, String name, double price, double vatRate, String description) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.description = description;
    }

    // Getter methods to retrieve item info
    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getVatRate() {
        return vatRate;
    }

    public String getDescription() {
        return description;
    }
}
