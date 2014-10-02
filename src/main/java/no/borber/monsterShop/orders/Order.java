package no.borber.monsterShop.orders;

import no.borber.monsterShop.basket.OrderId;

import java.util.Date;
import java.util.List;

public class Order {

    private Date date;
    private double sum;
    private List<OrderLineItem> orderLineItems;

    public Order(Date date, OrderId aggregateId, List<OrderLineItem> orderLineItems, double sum) {
        this.date = date;
        this.orderLineItems = orderLineItems;
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public List<OrderLineItem> getLineItems() {
        return orderLineItems;
    }
}
