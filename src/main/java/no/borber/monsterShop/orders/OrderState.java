package no.borber.monsterShop.orders;

import no.borber.monsterShop.basket.OrderId;

public class OrderState {
    private final OrderId orderId;

    public OrderState(OrderId orderId) {
        this.orderId = orderId;
    }
}
