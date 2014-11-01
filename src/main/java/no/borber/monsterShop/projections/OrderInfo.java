package no.borber.monsterShop.projections;

import no.borber.monsterShop.application.order.OrderLineItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderInfo {

    private LocalDateTime orderTime;
    private double sum;
    private String orderId;
    private List<OrderLineItemInfo> orderLineItems;
    private boolean canceled;

    public OrderInfo(LocalDateTime orderTime, String orderId, List<OrderLineItemInfo> orderLineItems, double sum) {
        this.orderTime = orderTime;
        this.orderId = orderId;
        this.orderLineItems = orderLineItems;
        this.sum = sum;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getFormattedOrderTime(){
        return orderTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public double getSum() {
        return sum;
    }

    public List<OrderLineItemInfo> getOrderLineItems() {
        return orderLineItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
