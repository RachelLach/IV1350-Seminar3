package se.kth.iv1350.pos.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pos.controller.Controller;
/**
 * Test suite for the {@link Receipt} class. Validates the generation of receipt text
 * and checks for correctness of sale information formatting.
 */
public class ReceiptTest {

    private Receipt receipt;

    /**
     * Sets up a {@link Receipt} instance using a mock {@link Sale} with one item.
     */
    @BeforeEach
    void setUp() {
        ItemDTO itemDTO = new ItemDTO("abc123", "Bread", 20.0, 0.12, "Wheat Bread");
        Item item = new Item(itemDTO);
        Sale sale = new Sale();
        sale.addItem(itemDTO);
        sale.addItem(itemDTO);
        sale.endSale();
        receipt = new Receipt(sale);
    }

    /**
     * Tests that the receipt's string representation includes the expected header and footer.
     */
    @Test
    void testReceiptIncludesHeaderAndFooter() {
        String output = receipt.toString();
        assertTrue(output.contains("Begin receipt"), "Receipt should start with a header.");
        assertTrue(output.contains("End receipt"), "Receipt should end with a footer.");
    }

    /**
     * Tests that the receipt contains the correct item details.
     */
    @Test
    void testReceiptIncludesItemDetails() {
        String output = receipt.toString();
        assertTrue(output.contains("Bread"), "Receipt should include the item name.");
        assertTrue(output.contains("2 x 20.0"), "Receipt should show correct quantity and unit price.");
        assertTrue(output.contains("= 40.0"), "Receipt should show correct line total for item.");
    }

    /**
     * Tests that the receipt includes total, VAT, cash amount, and change.
     */
    @Test
    void testReceiptIncludesTotalsAndPaymentInfo() {
        String output = receipt.toString();
        assertTrue(output.contains("Total: 40.0"), "Receipt should include the correct total.");
        assertTrue(output.contains("VAT: 4.8"), "Receipt should include the correct VAT.");
        assertTrue(output.contains("Cash: 100"), "Receipt should include the cash payment.");
        assertTrue(output.contains("Change: 60.0"), "Receipt should include the correct change.");
    }
}
