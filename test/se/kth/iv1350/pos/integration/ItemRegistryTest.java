package se.kth.iv1350.pos.integration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {

    @Test
    void testFindExistingItem() {
        ItemRegistry registry = new ItemRegistry();
        ItemDTO item = registry.findItem("abc123");

        assertNotNull(item);
        assertEquals("abc123", item.getItemID());
        assertEquals("BigWheel Oatmeal", item.getName());
    }

    @Test
    void testFindNonExistingItem() {

        ItemRegistry registry = new ItemRegistry();
        ItemDTO item = registry.findItem("999");
        assertNull(item);
    }
}
