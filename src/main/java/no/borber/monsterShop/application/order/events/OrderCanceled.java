package no.borber.monsterShop.application.order.events;

public class OrderCanceled extends OrderEvent {
    public OrderCanceled(String orderId) {
        super(orderId);
    }
}
