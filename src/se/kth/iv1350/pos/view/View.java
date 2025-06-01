package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.util.Logger;
import se.kth.iv1350.pos.util.FileLogger;
import se.kth.iv1350.pos.integration.ItemRegistry;

/**
 * The {@code View} class is a placeholder for the user interface of the POS system.
 * It simulates a simple flow of a sale, such as adding items and handling exceptions,
 * and demonstrates how the controller and logging mechanisms work.
 */
public class View {
    private final Logger logger;
    private final Controller controller;

    /**
     * Creates a new instance of the view and simulates a user performing a sale.
     * Demonstrates adding items, handling errors such as missing items or database issues,
     * and outputs the total cost and receipt.
     * @param controller The controller that manages the business logic of the sale.
     */
    public View(Controller controller) {
        this.controller = controller;
        this.logger = FileLogger.getInstance(); // Singleton logger instance

        runFakeExecution(); // Call simulation logic
    }

    /**
     * Default constructor that creates its own controller and logger.
     */
    public View() {
        this.logger = FileLogger.getInstance();  // or new FileLogger("logfile.txt");
        ItemRegistry registry = new ItemRegistry();  // create item registry
        this.controller = new Controller(registry, logger);

        runFakeExecution();  // run simulation
    }


    /**
     * Simulates a fake execution of a sale: adding items and handling errors.
     */
    private void runFakeExecution() {
        controller.startSale();

        try {
            System.out.println("Add 1 item with item id abc123:");
            ItemDTO item = controller.addItem("abc123");
            System.out.println(item.getName() + " " + item.getPrice() + " SEK");
            System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
            System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");

            System.out.println("Add 1 item with item id invalid123:");
            item = controller.addItem("invalid123");
            System.out.println(item.getName() + " " + item.getPrice() + " SEK");
        } catch (ItemNotFoundException | DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e);
        }

        try {
            System.out.println("Add 1 item with item id def456:");
            ItemDTO item = controller.addItem("def456");
            System.out.println(item.getName() + " " + item.getPrice() + " SEK");
            System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
            System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");
        } catch (ItemNotFoundException | DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e);
        }

        try {
            System.out.println("Add 1 item with item id dberror:");
            ItemDTO item = controller.addItem("dberror");
            System.out.println(item.getName() + " " + item.getPrice() + " SEK");
        } catch (ItemNotFoundException | DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e);
        }

        System.out.println("End sale:");
        System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK\n");
        System.out.println(controller.endSale());
    }

    /**
     * Formats a numeric amount to two decimal places and replaces the dot with a colon.
     * For example, 99.99 becomes "99:99".
     * @param amount The amount to format.
     * @return A string representation of the amount formatted with a colon as the decimal separator.
     */
    private String format(double amount) {
        return String.format("%.2f", amount).replace('.', ':');
    }
}
