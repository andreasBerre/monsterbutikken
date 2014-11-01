package no.borber.monsterShop.orders;

import no.borber.serialized.OrderEvent;

public class OrderCanceled extends OrderEvent {
    public OrderCanceled(OrderId orderId) {
        super(orderId);
    }
}
