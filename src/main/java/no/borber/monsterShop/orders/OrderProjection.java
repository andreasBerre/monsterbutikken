package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;
import no.borber.serialized.Event;
import no.borber.serialized.OrderCreated;

import java.util.*;

public class OrderProjection extends Projection{
    private Map<OrderId, Order> ordersByOrderId = new HashMap<>();
    private Map<CustomerId, List<Order>> ordersByCustomerId = new HashMap<>();

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
            handleOrderCreated((OrderCreated) event);
        } else if (event instanceof OrderCanceled){
            handleOrderCanceled((OrderCanceled) event);
        }
    }

    private void handleOrderCreated(OrderCreated orderCreated) {
        Order order = new Order(
                orderCreated.getOrderTime(),
                orderCreated.getAggregateId(),
                orderCreated.getOrderLineItems(),
                calculateTotal(orderCreated.getOrderLineItems())
        );

        ordersByOrderId.put(order.getOrderId(), order);

        if (ordersByCustomerId.get(orderCreated.getCustomerId()) == null)
            ordersByCustomerId.put(orderCreated.getCustomerId(), new ArrayList<>());
        ordersByCustomerId.get(orderCreated.getCustomerId()).add(order);
    }

    private void handleOrderCanceled(OrderCanceled orderCanceled) {
        ordersByOrderId.get(orderCanceled.getAggregateId()).setCanceled(true);
    }

    private double calculateTotal(List<OrderLineItem> orderLineItems) {
        double cost = 0;
        for (OrderLineItem orderLineItem : orderLineItems) {
            cost += orderLineItem.getCount() * orderLineItem.getMonsterTypePrice();
        }
        return cost;
    }

    public List<Order> getOrders(CustomerId customerId) {
        List<Order> customerOrders = ordersByCustomerId.get(customerId);
        return customerOrders != null ? customerOrders : Collections.emptyList();
    }

    public Optional<Order> getOrder(final CustomerId customerId, final OrderId orderId) {
        return ordersByCustomerId.get(customerId).stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst();
    }
}
