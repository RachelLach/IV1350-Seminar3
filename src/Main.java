import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemRegistry;
import se.kth.iv1350.pos.view.TotalRevenueFileOutput;
import se.kth.iv1350.pos.view.TotalRevenueView;
import se.kth.iv1350.pos.view.View;

public class Main {
    public static void main(String[] args) {
        ItemRegistry itemRegistry = new ItemRegistry();
        Controller controller = new Controller(itemRegistry);

        // Create and register observers BEFORE sales start
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput("totalRevenue.log");

        controller.addRevenueObserver(totalRevenueView);
        controller.addRevenueObserver(totalRevenueFileOutput);

        new View(controller);
    }
}
