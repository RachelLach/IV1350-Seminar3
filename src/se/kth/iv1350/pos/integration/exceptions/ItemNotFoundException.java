package se.kth.iv1350.pos.integration.exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemID) {
        super("Item with ID '" + itemID + "' was not found.");
    }
}
