package no.borber.monsterShop.application.order;

import java.time.LocalDateTime;

public class OrderState {
    private final String orderId;
    private LocalDateTime orderTime;
    private String customerId;
    private boolean canceled = false;

    public OrderState(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCanceled() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
