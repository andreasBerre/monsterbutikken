package no.borber.monsterShop.orders;

import java.util.Date;
import java.util.List;

public class Order {

    private Date date;
    private double sum;
    private List<OrderLineItem> orderLineItems;

    public Order(Date date, double sum, List<OrderLineItem> orderLineItems) {
        this.date = date;
        this.sum = sum;
        this.orderLineItems = orderLineItems;
    }

    public Date getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }
}
