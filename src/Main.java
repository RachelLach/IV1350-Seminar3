import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.view.View;

/**
 * The entry point for the POS (Point of Sale) application.
 */
public class Main {
    public static void main(String[] args) {
        ItemRegistry itemRegistry = new ItemRegistry();
        Controller controller = new Controller(itemRegistry);
        new View(controller);
    }
}
