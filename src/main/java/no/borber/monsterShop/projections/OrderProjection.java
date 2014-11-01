package no.borber.monsterShop.projections;

import no.borber.monsterShop.application.order.OrderCanceled;
import no.borber.monsterShop.application.order.OrderLineItem;
import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;
import no.borber.serialized.Event;
import no.borber.serialized.OrderCreated;

import java.util.*;
import java.util.stream.Collectors;

public class OrderProjection extends Projection{
    private Map<String, OrderInfo> ordersByOrderId = new HashMap<>();
    private Map<String, List<OrderInfo>> ordersByCustomerId = new HashMap<>();

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
        OrderInfo order = new OrderInfo(
                orderCreated.getOrderTime(),
                orderCreated.getAggregateId(),
                getOrderLineItemInfos(orderCreated.getOrderLineItems()),
                calculateTotal(orderCreated.getOrderLineItems())
        );

        ordersByOrderId.put(order.getOrderId(), order);

        if (ordersByCustomerId.get(orderCreated.getCustomerId()) == null)
            ordersByCustomerId.put(orderCreated.getCustomerId(), new ArrayList<>());
        ordersByCustomerId.get(orderCreated.getCustomerId()).add(order);
    }

    private List<OrderLineItemInfo> getOrderLineItemInfos(List<OrderLineItem> orderLineItems) {
        return orderLineItems.stream()
                .map(item -> new OrderLineItemInfo(item.getMonsterType(), item.getCount(), item.getMonsterTypePrice()))
                .collect(Collectors.toList());
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

    public List<OrderInfo> getOrders(final String customerId) {
        List<OrderInfo> customerOrders = ordersByCustomerId.get(customerId);
        return customerOrders != null ? customerOrders : Collections.emptyList();
    }

    public Optional<OrderInfo> getOrder(final String customerId, final String orderId) {
        return ordersByCustomerId.get(customerId).stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst();
    }
}
