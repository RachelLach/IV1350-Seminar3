package se.kth.iv1350.pos.model;
import java.time.LocalDateTime;

/**
 * Represents a receipt for a completed sale.
 * The receipt includes details such as items purchased, quantities, total cost, VAT,
 * and a timestamp of the sale.
 */
public class Receipt {
    private final Sale sale;

    /**
     * Creates a new {@code Receipt} instance for the given sale.
     * @param sale The {@link Sale} object containing the items and totals for this transaction.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
    }
    /**
     * Generates a textual representation of the receipt.
     * The receipt includes:
     * <ul>
     *     <li>Date and time of the sale</li>
     *     <li>List of items with quantity, unit price, and subtotal</li>
     *     <li>Total cost</li>
     *     <li>Total VAT</li>
     *     <li>Assumed cash payment of 100 SEK</li>
     *     <li>Calculated change</li>
     * </ul>
     * @return A formatted {@code String} representing the receipt details.
     */
    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("------------------ Begin receipt ------------------\n")
                .append("Time of Sale: ").append(LocalDateTime.now()).append("\n\n");

        for (Item item : sale.getItems()) {
            receipt.append(item.getItemInfo().getName())
                    .append(" ").append(item.getQuantity())
                    .append(" x ").append(item.getItemInfo().getPrice())
                    .append(" = ").append(item.getItemInfo().getPrice() * item.getQuantity())
                    .append(" SEK\n");
        }

        receipt.append("\nTotal: ").append(sale.getTotal())
                .append(" SEK\nVAT: ").append(sale.getTotalVAT())
                .append(" SEK\n")
                .append("Cash: 100 SEK\n")
                .append("Change: ").append(100 - sale.getTotal())
                .append(" SEK\n------------------ End receipt --------------------\n");

        return receipt.toString();
    }
}
