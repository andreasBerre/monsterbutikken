package no.borber.serialized;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.orders.OrderEvent;
import no.borber.monsterShop.orders.OrderId;
import no.borber.monsterShop.orders.OrderLineItem;

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
