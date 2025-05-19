package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.integration.exceptions.DatabaseException;
import se.kth.iv1350.pos.integration.exceptions.ItemNotFoundException;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.util.Logger;
import se.kth.iv1350.pos.util.FileLogger;

public class View {
    private final Logger logger; // Logger instance

    public View(Controller controller) {
        this.logger = new FileLogger("logfile.log");
        // Or new FileLogger(); if implemented

        controller.startSale();

        try {
            System.out.println("Add 1 item with item id abc123:");
            System.out.println(controller.addItem("abc123"));
            System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
            System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");

            System.out.println("Add 1 item with item id invalid123:");
            System.out.println(controller.addItem("invalid123"));
        } catch (ItemNotFoundException | DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e); // ← Log exception here
        }

        try {
            System.out.println("Add 1 item with item id def456:");
            System.out.println(controller.addItem("def456"));
            System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK");
            System.out.println("Total VAT: " + format(controller.getVatTotal()) + " SEK\n");
        } catch (ItemNotFoundException | DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e); // ← Log exception here
        }

        try {
            System.out.println("Add 1 item with item id dberror:");
            System.out.println(controller.addItem("dberror"));
        } catch (ItemNotFoundException | DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
            logger.logException(e); // ← Log exception here
        }

        System.out.println("End sale:");
        System.out.println("Total cost (incl VAT): " + format(controller.getTotal()) + " SEK\n");
        System.out.println(controller.endSale());
    }

    private String format(double amount) {
        return String.format("%.2f", amount).replace('.', ':');
    }
}
