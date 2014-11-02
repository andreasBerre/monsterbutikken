package no.borber.monsterShop.application.order.events;

import no.borber.monsterShop.application.order.OrderLineItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreated extends OrderEvent {
    private List<OrderLineItem> lineItems;
    private String customerId;
    private LocalDateTime timePlaced;

    public OrderCreated(String orderId, String customerId, List<OrderLineItem> lineItems, LocalDateTime timePlaced) {
        super(orderId);
        this.customerId = customerId;
        this.lineItems = lineItems;
        this.timePlaced = timePlaced;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }
}
