package se.kth.iv1350.pos.integration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link ItemRegistry} class. Verifies correct behavior for
 * item retrieval and error handling in simulated scenarios.
 */
public class ItemRegistryTest {

    private ItemRegistry itemRegistry;

    /**
     * Sets up a new instance of {@link ItemRegistry} before each test.
     */
    @BeforeEach
    void setUp() {
        itemRegistry = new ItemRegistry();
    }

    /**
     * Tests that an existing item is correctly retrieved from the registry.
     */
    @Test
    void testFindExistingItem() throws ItemNotFoundException, DatabaseException {
        ItemDTO item = itemRegistry.findItem("abc123");
        assertNotNull(item, "The returned item should not be null.");
        assertEquals("abc123", item.getItemID(), "Item ID should match the input.");
        assertEquals("BigWheel Oatmeal", item.getName(), "Item name should match expected value.");
    }

    /**
     * Tests that requesting a non-existent item ID throws an {@link ItemNotFoundException}.
     */
    @Test
    void testFindNonExistingItemThrowsException() {
        assertThrows(ItemNotFoundException.class, () -> itemRegistry.findItem("xyz999"),
                "Should throw ItemNotFoundException for unknown item ID.");
    }

    /**
     * Tests that the simulated database error condition throws a {@link DatabaseException}.
     */
    @Test
    void testDatabaseErrorThrowsException() {
        assertThrows(DatabaseException.class, () -> itemRegistry.findItem("dberror"),
                "Should throw DatabaseException when 'dberror' ID is used.");
    }
}
