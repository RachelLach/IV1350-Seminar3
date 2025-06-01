package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a simulated external item registry used in a point of sale system.
 * Acts as a database for retrieving information about items based on their ID.
 * This class simulates the database behavior with a local HashMap.
 */
public class ItemRegistry {
    private final Map<String, ItemDTO> itemDatabase = new HashMap<>();

    /**
     * Creates a new instance of {@code ItemRegistry} and initializes it with a
     * predefined set of items. This simulates a basic database with hardcoded entries.
     */
    public ItemRegistry() {
        itemDatabase.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "Healthy Oatmeal"));
        itemDatabase.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, "Delicious Blueberry"));
    }

    /**
     * Searches for an item in the registry using the provided item ID.
     * @param itemID The identifier of the item to look up.
     * @return The {@link ItemDTO} corresponding to the provided ID.
     * @throws ItemNotFoundException If no item with the specified ID exists in the registry.
     * @throws DatabaseException If there is a simulated database failure (e.g., if the itemID is "dberror").
     */
    public ItemDTO findItem(String itemID) throws ItemNotFoundException, DatabaseException {
        if (itemID.equals("dberror")) {
            throw new DatabaseException("Database connection error occurred.");
        }
        ItemDTO item = itemDatabase.get(itemID);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID " + itemID + " not found.");
        }
        return item;
    }
}
