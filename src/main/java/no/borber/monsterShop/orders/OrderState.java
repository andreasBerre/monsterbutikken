package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;

import java.time.LocalDateTime;

public class OrderState {
    private final OrderId orderId;
    private LocalDateTime orderTime;
    private CustomerId customerId;

    public OrderState(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }
}
