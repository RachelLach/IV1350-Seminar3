import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.util.FileLogger;
import se.kth.iv1350.pos.view.TotalRevenueFileOutput;
import se.kth.iv1350.pos.view.TotalRevenueView;
import se.kth.iv1350.pos.view.View;
import se.kth.iv1350.pos.util.Logger;

/**
 * The entry point of the POS application.
 * Initializes core components such as the item registry, controller,
 * revenue observers, and view. Starts the application simulation.
 */
public class Main {

    /**
     * This is the main method that starts the POS program.
     * It creates important parts of the system like the controller, logger, and item registry.
     * It also sets up two revenue observers: one that prints to screen, and one that writes to a file.
     * Finally, it creates the {@link View} which simulates a sale and prints the results.
     * @param args Not used in this application.
     */
    public static void main(String[] args) {
        ItemRegistry itemRegistry = new ItemRegistry();
        Logger logger = FileLogger.getInstance();

        Controller controller = new Controller(itemRegistry, logger);

        TotalRevenueView totalRevenueView = new TotalRevenueView();
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput("totalRevenue.log");

        controller.addRevenueObserver(totalRevenueView);
        controller.addRevenueObserver(totalRevenueFileOutput);

        new View(controller);
    }
}
