package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.controller.Controller;


public class View {
    public View(Controller controller) {
        controller.startSale();

        System.out.println("Add 1 item with item id abc123:");
        System.out.println(controller.addItem("abc123"));
        System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
        System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");

        // Add same item again
        System.out.println("Add 1 item with item id abc123:");
        System.out.println(controller.addItem("abc123"));
        System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
        System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");

        // Add second item
        System.out.println("Add 1 item with item id def456:");
        System.out.println(controller.addItem("def456"));
        System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
        System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");

        // End sale and print receipt
        System.out.println("End sale:");
        System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK\n");
        System.out.println(controller.endSale());
    }

    private String format(double amount) {
        return String.format("%.2f", amount).replace('.', ':');
    }
}
