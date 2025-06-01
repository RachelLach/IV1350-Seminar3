package se.kth.iv1350.pos.view;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class checks that the {@link View} class prints the correct information to System.out.
 * It captures console output and verifies that expected texts appear.
 */
class ViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * This method runs before each test. It redirects System.out to a byte stream,
     * so the test can check what gets printed to the console.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * This method runs after each test. It resets System.out back to normal
     * to avoid affecting other tests or parts of the program.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * This test checks that the {@link View} class prints expected information when simulating a sale.
     * It verifies that item IDs, totals, error messages, and the receipt are included in the output.
     */
    @Test
    void testViewPrintsCorrectInformation() {

        new View();
        String output = outContent.toString();

        assertTrue(output.contains("Add 1 item with item id abc123:"), "Should mention abc123");
        assertTrue(output.contains("Total cost (incl VAT):"), "Should print total cost");
        assertTrue(output.contains("Total VAT:"), "Should print total VAT");

        assertTrue(output.contains("Error: Could not find item with ID: invalid123"), "Should show error for invalid item");
        assertTrue(output.contains("Error: Database error occurred while accessing item with ID: dberror"), "Should show DB error");

        assertTrue(output.contains("End sale:"), "Should indicate sale ending");
        assertTrue(output.contains("Receipt"), "Should print the receipt or at least summary");
    }
}
