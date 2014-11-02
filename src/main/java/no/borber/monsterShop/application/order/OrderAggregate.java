package no.borber.monsterShop.application.order;

import no.borber.monsterShop.application.Aggregate;
import no.borber.monsterShop.application.CommandValidationException;
import no.borber.monsterShop.application.basket.BasketLineItem;
import no.borber.monsterShop.application.monster.MonsterTypesRepo;
import no.borber.monsterShop.application.order.events.OrderCanceled;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.application.order.events.OrderCreated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAggregate extends Aggregate {
    public static final int CANCEL_WINDOW_IN_MINUTES = 5;
    private OrderState orderState;

    public OrderAggregate(List<Event> events) {
        for (Event event : events) {
            if (event instanceof OrderCreated) {
                orderState = new OrderState(event.getAggregateId());
                orderState.setCustomerId(((OrderCreated) event).getCustomerId());
                orderState.setOrderTime(((OrderCreated) event).getTimePlaced());
            } else if (event instanceof OrderCanceled){
                orderState.setCanceled();
            }
        }
    }

    public void createOrder(String orderId, String customerId, List<BasketLineItem> basketLineItems, LocalDateTime orderTime) {
        if (orderState != null) {
            throw new CommandValidationException("Attempt to create order with pre-existing id, create order failed");
        } else {
            ArrayList<OrderLineItem> orderLineItems = new ArrayList<>();
            for (BasketLineItem basketLineItem : basketLineItems) {
                orderLineItems.add(new OrderLineItem(
                        basketLineItem.getMonsterType(),
                        basketLineItem.getQuantity(),
                        MonsterTypesRepo.getMonsterType(basketLineItem.getMonsterType()).getPrice()));
            }
            derivedEvents.add(new OrderCreated(orderId, customerId, orderLineItems, orderTime));
        }
    }

    public void cancelOrder(String customerId) {
        if (orderState == null)
            throw new CommandValidationException("Attempt to cancel non-existing order, cancel order failed");
        else if (orderState.isCanceled())
            throw new CommandValidationException("Attempt to cancel a order which has already been canceled, cancel order failed");
        else if (!orderState.getCustomerId().equals(customerId))
            throw new CommandValidationException("Attempt to cancel order that does not correspond to supplied customerId, cancel order failed");
        else if (orderState.getOrderTime().minusMinutes(CANCEL_WINDOW_IN_MINUTES).isAfter(LocalDateTime.now()))
            throw new CommandValidationException("Attempt to cancel order older than " + CANCEL_WINDOW_IN_MINUTES + ", cancel order failed");
        else
            derivedEvents.add(new OrderCanceled(orderState.getOrderId()));
    }
}
