package se.kth.iv1350.pos.integration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ItemDTO}.
 * Tests the creation of an {@code ItemDTO} object and its getter methods.
 */
class ItemDTOTest {

    /**
     * Tests that an {@code ItemDTO} instance correctly stores and returns
     * the provided item ID, name, price, VAT rate, and description.
     */
    @Test
    void testItemDTOGetters() {
        String expectedItemID = "12345";
        String expectedName = "Apple";
        double expectedPrice = 10.0;
        double expectedVatRate = 0.25;
        String expectedDescription = "Fresh red apple";

        ItemDTO item = new ItemDTO(expectedItemID, expectedName, expectedPrice, expectedVatRate, expectedDescription);

        assertEquals(expectedItemID, item.getItemID(), "Item ID should match the constructor argument");
        assertEquals(expectedName, item.getName(), "Name should match the constructor argument");
        assertEquals(expectedPrice, item.getPrice(), "Price should match the constructor argument");
        assertEquals(expectedVatRate, item.getVatRate(), "VAT rate should match the constructor argument");
        assertEquals(expectedDescription, item.getDescription(), "Description should match the constructor argument");
    }
}
