package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.pos.model.observers.TotalRevenueObserver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Controller} class using manual stub implementations
 * instead of external mocking libraries like Mockito.
 */
class ControllerTest {
    private Controller controller;
    private StubItemRegistry stubRegistry;

    /**
     * Sets up a new {@code Controller} with a stubbed item registry before each test.
     */
    @BeforeEach
    void setUp() {
        stubRegistry = new StubItemRegistry();
        controller = new Controller(stubRegistry);
    }

    /**
     * Tests that an item can be successfully added after starting a sale.
     * Verifies the returned item has the correct name.
     *
     * @throws ItemNotFoundException if item ID is not found (should not happen here)
     * @throws DatabaseException     if database access fails (should not happen here)
     */
    @Test
    void testStartSaleAndAddItem() throws ItemNotFoundException, DatabaseException {
        controller.startSale();
        ItemDTO item = controller.addItem("valid");
        assertEquals("Test Item", item.getName());
    }

    /**
     * Tests that trying to add an invalid item ID throws an {@code ItemNotFoundException}.
     */
    @Test
    void testAddItemThrowsItemNotFoundException() {
        controller.startSale();
        assertThrows(ItemNotFoundException.class, () -> controller.addItem("invalid"));
    }

    /**
     * Tests that the total and VAT values are correctly calculated for the added item.
     *
     * @throws ItemNotFoundException if item ID is not found (should not happen here)
     * @throws DatabaseException     if database access fails (should not happen here)
     */
    @Test
    void testGetTotalAndVatTotal() throws ItemNotFoundException, DatabaseException {
        controller.startSale();
        controller.addItem("valid");

        assertEquals(100.0, controller.getTotal(), 0.01);
        assertEquals(25.0, controller.getVatTotal(), 0.01);
    }

    /**
     * Tests that a registered {@code TotalRevenueObserver} is notified after a sale ends.
     *
     * @throws ItemNotFoundException if item ID is not found (should not happen here)
     * @throws DatabaseException     if database access fails (should not happen here)
     */
    @Test
    void testEndSaleNotifiesObserver() throws ItemNotFoundException, DatabaseException {
        StubRevenueObserver observer = new StubRevenueObserver();
        controller.addRevenueObserver(observer);

        controller.startSale();
        controller.addItem("valid");
        controller.endSale();

        assertEquals(100.0, observer.updatedRevenue, 0.01);
    }

    /**
     * A stub implementation of {@link ItemRegistry} used for testing the {@link Controller} class
     * without relying on external dependencies or a real database.
     *
     * This stub simulates the behavior of finding an item by its ID. It returns a hardcoded
     * {@link ItemDTO} for the ID "valid" and throws an {@link ItemNotFoundException} for any other ID.
     */
    private static class StubItemRegistry extends ItemRegistry {
        /**
         * Simulates the lookup of an item by its identifier.
         *
         * @param itemID The ID of the item to look up.
         * @return An {@code ItemDTO} object with hardcoded values if the ID is "valid".
         * @throws ItemNotFoundException If the item ID is not "valid".
         */
        @Override
        public ItemDTO findItem(String itemID) throws ItemNotFoundException {
            if ("valid".equals(itemID)) {
                return new ItemDTO(
                        "valid",           // ID
                        "Test Item",       // Name
                        100.0,             // Price
                        0.25,              // VAT rate
                        "A basic test item" // Description
                );
            }
            throw new ItemNotFoundException("Item not found: " + itemID);
        }
    }


    /**
     * A stub implementation of {@code TotalRevenueObserver} that records revenue updates.
     */
    private static class StubRevenueObserver implements TotalRevenueObserver {
        double updatedRevenue = 0;

        @Override
        public void updateTotalRevenue(double revenue) {
            this.updatedRevenue = revenue;
        }
    }
}
