package se.kth.iv1350.pos.model;
import java.time.LocalDateTime;

public class Receipt {
    private final Sale sale;

    public Receipt(Sale sale) {
        this.sale = sale;
    }

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
