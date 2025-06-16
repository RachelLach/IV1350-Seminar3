import org.junit.jupiter.api.Test;

/**
 * Test class for the {@link Main} class.
 * <p>
 * This class verifies that the {@code main} method in the {@link Main} class
 * can execute without throwing any exceptions. It acts as a basic smoke test
 * to ensure that the application's entry point is properly configured and runs successfully.
 * </p>
 */
class MainTest {

    /**
     * Tests that the {@link Main#main(String[])} method runs without throwing any exceptions.
     * <p>
     * This is a simple test to confirm that the application can start up correctly.
     * It does not validate output or behavior, only that no runtime exceptions occur during launch.
     * </p>
     */
    @Test
    public void main_shouldRunWithoutExceptions() {
        Main.main(new String[0]);
    }
}
