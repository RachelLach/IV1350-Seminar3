package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {
    private ItemRegistry itemRegistry;

    @BeforeEach
    void setUp() {
        itemRegistry = new ItemRegistry();
    }

    @Test
    void testFindItemWithValidID() throws ItemNotFoundException, DatabaseException {
        ItemDTO item = itemRegistry.findItem("abc123");
        assertNotNull(item, "Item should not be null for a valid ID.");
        assertEquals("abc123", item.getItemID(), "Item ID should match input.");
    }

    @Test
    void testFindItemThrowsItemNotFoundException() {
        String invalidItemID = "invalid123";
        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            itemRegistry.findItem(invalidItemID);
        });
        assertTrue(exception.getMessage().contains(invalidItemID), "Exception message should contain the invalid ID.");
    }

    @Test
    void testFindItemThrowsDatabaseException() {
        String dbErrorID = "dberror";
        Exception exception = assertThrows(DatabaseException.class, () -> {
            itemRegistry.findItem(dbErrorID);
        });
        assertTrue(exception.getMessage().toLowerCase().contains("database"), "Exception message should mention database.");
    }
}
