package no.borber.serialized;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.orders.OrderId;
import no.borber.monsterShop.orders.OrderLineItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreated extends OrderEvent {
    private List<OrderLineItem> orderLineItems;
    private CustomerId customerId;
    private LocalDateTime orderTime;

    public OrderCreated(OrderId orderId, CustomerId customerId, List<OrderLineItem> orderLineItems, LocalDateTime orderTime) {
        super(orderId);
        this.customerId = customerId;
        this.orderLineItems = orderLineItems;
        this.orderTime = orderTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}
