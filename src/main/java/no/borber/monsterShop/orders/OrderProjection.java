package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;

import java.util.*;

public class OrderProjection extends Projection{
    private Map<CustomerId, Map<String, Order>> orders = new HashMap<>();

    public OrderProjection(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    protected AggregateType getAggregateType() {
        return AggregateType.ORDER;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof OrderCreated){
            OrderCreated orderCreated = (OrderCreated) event;

            if (orders.get(orderCreated.getCustomerId()) == null)
                orders.put(orderCreated.getCustomerId(), new HashMap<String, Order>());

            orders.get(orderCreated.getCustomerId()).put(
                    orderCreated.getAggregateId().toString(),
                    new Order(
                            new Date(),
                            orderCreated.getAggregateId(),
                            orderCreated.getOrderLineItems(),
                            calculateTotal(orderCreated.getOrderLineItems())
                    )
            );
        }
    }

    private double calculateTotal(List<OrderLineItem> orderLineItems) {
        double cost = 0;
        for (OrderLineItem orderLineItem : orderLineItems) {
            cost += orderLineItem.getCount() * orderLineItem.getMonsterTypePrice();
        }
        return cost;
    }

    public Map<String, Order> getOrders(CustomerId customerId) {
        return orders.get(customerId);
    }

    public Order getOrder(CustomerId customerId, String orderId) {
        return orders.get(customerId).get(orderId);
    }
}
