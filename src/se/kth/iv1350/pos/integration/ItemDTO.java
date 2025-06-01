package se.kth.iv1350.pos.integration;

/**
 * A Data Transfer Object (DTO) that contains information about an item in the inventory system.
 * This class is used to pass item data between layers of the POS system (e.g., from the integration
 * layer to the model layer) without exposing internal implementation details.
 */
public class ItemDTO {
    private final String itemID;
    private final String name;
    private final double price;
    private final double vatRate;
    private final String description;

    /**
     * Creates a new instance of {@code ItemDTO} with the specified attributes.
     * @param itemID The unique identifier for the item.
     * @param name The name of the item.
     * @param price The base price of the item (excluding VAT).
     * @param vatRate The VAT (Value Added Tax) rate applied to the item (e.g., 0.25 for 25%).
     * @param description A brief description of the item.
     */
    public ItemDTO(String itemID, String name, double price, double vatRate, String description) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the item.
     * @return The item ID.
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Gets the name of the item.
     * @return The item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the base price of the item, excluding VAT.
     * @return The item's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the VAT (Value Added Tax) rate for the item.
     * @return The VAT rate as a decimal (e.g., 0.25 for 25%).
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * Gets a brief description of the item.
     * @return The item description.
     */
    public String getDescription() {
        return description;
    }
}
