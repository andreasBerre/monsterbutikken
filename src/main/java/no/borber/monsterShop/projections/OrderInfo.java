package no.borber.monsterShop.projections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderInfo {

    private final LocalDateTime timePlaced;
    private final String orderId;
    private List<OrderLineItemInfo> lineItems;
    private boolean canceled = false;

    public OrderInfo(LocalDateTime timePlaced, String orderId, List<OrderLineItemInfo> lineItems) {
        this.timePlaced = timePlaced;
        this.orderId = orderId;
        this.lineItems = lineItems;
    }

    public String getTimePlaced(){
        return timePlaced.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public double getTotal() {
        return lineItems.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }

    public List<OrderLineItemInfo> getLineItems() {
        return lineItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setAsCanceled() {
        this.canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
