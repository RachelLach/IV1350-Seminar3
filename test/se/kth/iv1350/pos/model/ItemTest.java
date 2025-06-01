package se.kth.iv1350.pos.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link Item} class. Validates behavior related to
 * item quantity and data encapsulation from {@link ItemDTO}.
 */
public class ItemTest {
    private Item item;
    private ItemDTO testItemDTO;

    /**
     * Sets up a new {@link Item} instance before each test.
     * Initializes it with a sample {@link ItemDTO}.
     */
    @BeforeEach
    void setUp() {
        testItemDTO = new ItemDTO("abc123", "Milk", 10.0, 0.12, "1L full cream milk");
        item = new Item(testItemDTO);
    }

    /**
     * Tests that the initial quantity of a newly created item is 1.
     */
    @Test
    void testInitialQuantityIsOne() {
        int expected = 1;
        int actual = item.getQuantity();
        assertEquals(expected, actual, "Quantity should be initialized to 1.");
    }

    /**
     * Tests that calling {@code increaseQuantity()} correctly increments the quantity.
     */
    @Test
    void testIncreaseQuantity() {
        item.increaseQuantity();
        int expected = 2;
        int actual = item.getQuantity();
        assertEquals(expected, actual, "Quantity should be incremented by 1.");
    }

    /**
     * Tests that the item retains the correct {@link ItemDTO} after initialization.
     */
    @Test
    void testGetItemInfo() {
        ItemDTO result = item.getItemInfo();
        assertSame(testItemDTO, result, "ItemDTO reference should be the same as the one passed to constructor.");
    }
}
