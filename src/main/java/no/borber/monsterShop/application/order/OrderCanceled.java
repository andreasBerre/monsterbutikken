package no.borber.monsterShop.application.order;

import no.borber.serialized.OrderEvent;

public class OrderCanceled extends OrderEvent {
    public OrderCanceled(String orderId) {
        super(orderId);
    }
}
