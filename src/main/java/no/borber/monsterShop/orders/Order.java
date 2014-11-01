package no.borber.monsterShop.orders;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {

    private LocalDateTime orderTime;
    private double sum;
    private OrderId orderId;
    private List<OrderLineItem> orderLineItems;
    private boolean canceled;

    public Order(LocalDateTime orderTime, OrderId orderId, List<OrderLineItem> orderLineItems, double sum) {
        this.orderTime = orderTime;
        this.orderId = orderId;
        this.orderLineItems = orderLineItems;
        this.sum = sum;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public double getSum() {
        return sum;
    }

    public List<OrderLineItem> getLineItems() {
        return orderLineItems;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
