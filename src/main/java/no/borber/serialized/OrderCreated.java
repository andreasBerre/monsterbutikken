package no.borber.serialized;

import no.borber.monsterShop.application.order.OrderLineItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreated extends OrderEvent {
    private List<OrderLineItem> orderLineItems;
    private String customerId;
    private LocalDateTime orderTime;

    public OrderCreated(String orderId, String customerId, List<OrderLineItem> orderLineItems, LocalDateTime orderTime) {
        super(orderId);
        this.customerId = customerId;
        this.orderLineItems = orderLineItems;
        this.orderTime = orderTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}
