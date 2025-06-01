package se.kth.iv1350.pos.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link Sale} class.
 * <p>
 * This class verifies the correct behavior of adding items,
 * calculating totals and VAT, and retrieving the list of items.
 * </p>
 */
public class SaleTest {
    private Sale sale;
    private ItemDTO item1;
    private ItemDTO item2;

    /**
     * Sets up a new {@link Sale} instance and sample {@link ItemDTO} objects
     * before each test.
     */
    @BeforeEach
    void setUp() {
        sale = new Sale();
        item1 = new ItemDTO("abc123", "Milk", 10.0, 0.12, "1L full cream milk");
        item2 = new ItemDTO("def456", "Bread", 20.0, 0.06, "Wheat bread");
    }

    /**
     * Tests that adding a new item to the sale adds it to the items list.
     */
    @Test
    void testAddNewItem() {
        sale.addItem(item1);
        List<Item> items = sale.getItems();

        assertEquals(1, items.size(), "There should be one item in the sale.");
        assertEquals(item1.getItemID(), items.get(0).getItemInfo().getItemID(), "The item ID should match the added item.");
        assertEquals(1, items.get(0).getQuantity(), "The quantity should be 1 for a newly added item.");
    }

    /**
     * Tests that adding the same item increases the quantity instead of adding a new item.
     */
    @Test
    void testAddExistingItemIncreasesQuantity() {
        sale.addItem(item1);
        sale.addItem(item1);
        List<Item> items = sale.getItems();

        assertEquals(1, items.size(), "There should still be only one item in the sale.");
        assertEquals(2, items.get(0).getQuantity(), "The quantity should increase to 2 after adding the same item twice.");
    }

    /**
     * Tests the calculation of the total price excluding VAT.
     */
    @Test
    void testGetTotal() {
        sale.addItem(item1);
        sale.addItem(item2);
        sale.addItem(item2);

        double expectedTotal = 50.0;
        assertEquals(expectedTotal, sale.getTotal(), 0.0001, "Total should be sum of item prices times quantities.");
    }

    /**
     * Tests the calculation of the total VAT for the sale.
     */
    @Test
    void testGetTotalVAT() {
        sale.addItem(item1);
        sale.addItem(item2);
        sale.addItem(item2);


        double expectedVAT = 3.6;
        assertEquals(expectedVAT, sale.getTotalVAT(), 0.0001, "Total VAT should be sum of item VAT amounts.");
    }

    /**
     * Tests that the items list returned is the same list that was added to.
     */
    @Test
    void testGetItems() {
        sale.addItem(item1);
        sale.addItem(item2);

        List<Item> items = sale.getItems();

        assertNotNull(items, "Items list should not be null.");
        assertEquals(2, items.size(), "There should be two items in the sale.");
    }
}
