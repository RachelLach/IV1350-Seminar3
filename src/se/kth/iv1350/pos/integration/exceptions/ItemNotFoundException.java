package se.kth.iv1350.pos.integration.exceptions;

/**
 * Thrown to indicate that an item with the specified identifier could not be found
 * in the item registry. This exception is typically used when a search for an item
 * in the external inventory system fails.
 */
public class ItemNotFoundException extends Exception {

    /**
     * Creates a new instance of {@code ItemNotFoundException} with a message
     * indicating the missing item ID.
     * @param itemID The identifier of the item that was not found.
     */
    public ItemNotFoundException(String itemID) {
        super("Item with ID '" + itemID + "' was not found.");
    }
}
