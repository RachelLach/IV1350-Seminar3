package se.kth.iv1350.pos.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link TotalRevenueView} class.
 * <p>
 * This class verifies that {@link TotalRevenueView} displays total revenue correctly
 * and handles errors as expected by checking its console output.
 * </p>
 */
class TotalRevenueViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private TotalRevenueView revenueView;

    /**
     * Redirects System.out to a byte stream before each test for output validation.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        revenueView = new TotalRevenueView();
    }

    /**
     * Tests that the {@link TotalRevenueView#doShowTotalRevenue(double)} method
     * correctly prints the formatted revenue to the console.
     */
    @Test
    void testDoShowTotalRevenue_DisplaysFormattedRevenue() {
        double sampleRevenue = 1234.567;
        revenueView.doShowTotalRevenue(sampleRevenue);

        String actualOutput = outContent.toString().trim();
        System.out.println("Captured output: " + actualOutput);  // Debug line

        String expectedOutput = "Total revenue so far: 1234,57 SEK";
        assertTrue(actualOutput.contains(expectedOutput),
                "Expected output to contain: " + expectedOutput + " but was: " + actualOutput);
    }


    /**
     * Tests that the {@link TotalRevenueView#handleErrors(Exception)} method
     * prints the exception message to the console when an error occurs.
     */
    @Test
    void testHandleErrors_DisplaysErrorMessage() {
        Exception testException = new Exception("Test error");
        revenueView.handleErrors(testException);

        String expectedOutput = "Could not display total revenue: Test error";
        assertTrue(outContent.toString().trim().contains(expectedOutput),
                "Expected output to contain: " + expectedOutput);
    }
}
