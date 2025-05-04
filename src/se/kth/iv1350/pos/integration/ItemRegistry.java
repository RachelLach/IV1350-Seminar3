package se.kth.iv1350.pos.integration;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private final Map<String, ItemDTO> itemDatabase = new HashMap<>();

    public ItemRegistry() {
        // Add items with full arguments, including description
        itemDatabase.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "Healthy Oatmeal"));
        itemDatabase.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, "Delicious Blueberry"));
    }

    public ItemDTO findItem(String itemID) {
        return itemDatabase.get(itemID);
    }
}
