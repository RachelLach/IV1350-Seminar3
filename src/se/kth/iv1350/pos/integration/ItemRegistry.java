package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private final Map<String, ItemDTO> itemDatabase = new HashMap<>();

    public ItemRegistry() {
        itemDatabase.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "Healthy Oatmeal"));
        itemDatabase.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, "Delicious Blueberry"));
    }

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
