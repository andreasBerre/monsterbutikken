package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.basket.OrderId;

import java.util.List;

public class OrderCreated extends OrderEvent {
    private List<OrderLineItem> orderLineItems;
    private CustomerId customerId;

    public OrderCreated(OrderId orderId, CustomerId customerId, List<OrderLineItem> orderLineItems) {
        super(orderId);
        this.customerId = customerId;
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
}
