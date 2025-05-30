package se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        ItemRegistry itemRegistry = new ItemRegistry();
        controller = new Controller(itemRegistry);  // Pass ItemRegistry here
    }

    @Test
    void testAddItemWithValidID() throws ItemNotFoundException, DatabaseException {
        controller.startSale();
        String result = controller.addItem("abc123");
        assertTrue(result.contains("BigWheel Oatmeal"), "Should return the correct item name");
    }

    @Test
    void testAddItemThrowsItemNotFoundException() {
        controller.startSale();
        assertThrows(ItemNotFoundException.class, () -> {
            controller.addItem("invalid123");
        });
    }

    @Test
    void testAddItemThrowsDatabaseException() {
        controller.startSale();
        assertThrows(DatabaseException.class, () -> {
            controller.addItem("dberror");
        });
    }
}
