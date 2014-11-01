package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.basket.Aggregate;
import no.borber.monsterShop.basket.BasketLineItem;
import no.borber.monsterShop.eventStore.CommandValidationException;
import no.borber.monsterShop.monsterTypes.MonsterTypesRepo;
import no.borber.serialized.Event;
import no.borber.serialized.OrderCreated;
import no.borber.serialized.OrderEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAggregate extends Aggregate{
    public static final int CANCEL_WINDOW_IN_MINUTES = 5;
    private OrderState orderState;

    public OrderAggregate(List<Event> events) {
        for (Event event : events) {
            if (event instanceof OrderCreated) {
                orderState = new OrderState(((OrderEvent) event).getAggregateId());
                orderState.setCustomerId(((OrderCreated) event).getCustomerId());
                orderState.setOrderTime(((OrderCreated) event).getOrderTime());
            }
        }
    }

    public void createOrder(OrderId orderId, CustomerId customerId, List<BasketLineItem> basketLineItems, LocalDateTime orderTime) {
        if (orderState != null) {
            throw new CommandValidationException("Attempt to create order with pre-existing id, create order failed");
        } else {
            ArrayList<OrderLineItem> orderLineItems = new ArrayList<>();
            for (BasketLineItem basketLineItem : basketLineItems) {
                orderLineItems.add(new OrderLineItem(
                        basketLineItem.getMonsterType(),
                        basketLineItem.getCount(),
                        MonsterTypesRepo.getMonsterType(basketLineItem.getMonsterType()).getPrice()));
            }
            derivedEvents.add(new OrderCreated(orderId, customerId, orderLineItems, orderTime));
        }
    }

    public void cancelOrder(CustomerId customerId) {
        if (orderState == null)
            throw new CommandValidationException("Attempt to cancel non-existing order, cancel order failed");
        else if (!orderState.getCustomerId().equals(customerId))
            throw new CommandValidationException("Attempt to cancel order that does not correspond to supplied customerId, cancel order failed");
        else if (orderState.getOrderTime().plusMinutes(CANCEL_WINDOW_IN_MINUTES).isAfter(LocalDateTime.now()))
            throw new CommandValidationException("Attempt to cancel order older than " + CANCEL_WINDOW_IN_MINUTES + ", cancel order failed");
        else
            derivedEvents.add(new OrderCanceled(orderState.getOrderId()));
    }
}
