package se.kth.iv1350.pos.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemRegistry;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        ItemRegistry itemRegistry = new ItemRegistry();
        controller = new Controller(itemRegistry);
        controller.startSale();
    }

    @Test
    void testAddItem() {
        String result = controller.addItem("abc123");
        assertTrue(result.contains("BigWheel Oatmeal"));
    }

    @Test
    void testAddInvalidItem() {
        String result = controller.addItem("invalid123");
        assertEquals("Item not found.", result);
    }

    @Test
    void testGetTotal() {
        controller.addItem("abc123");
        double total = controller.getTotal();
        assertEquals(29.90, total, 0.01);
    }

    @Test
    void testGetVatTotal() {
        controller.addItem("abc123");
        double vat = controller.getVatTotal();
        assertEquals(1.7939999999999998, vat, 0.01);
    }

    @Test
    void testEndSale() {
        controller.addItem("abc123");
        String receipt = controller.endSale();
        assertTrue(receipt.contains("BigWheel Oatmeal"));
        assertTrue(receipt.contains("Total"));
    }
}
