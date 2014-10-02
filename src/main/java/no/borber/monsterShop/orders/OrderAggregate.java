package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.basket.Aggregate;
import no.borber.monsterShop.basket.BasketLineItem;
import no.borber.monsterShop.basket.OrderId;
import no.borber.monsterShop.eventStore.CommandValidationException;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.monsterTypes.MonsterTypesRepo;

import java.util.ArrayList;
import java.util.List;

public class OrderAggregate extends Aggregate{
    private OrderState orderState;

    public OrderAggregate(List<Event> events) {
        for (Event event : events) {
            if (event instanceof OrderCreated)
                orderState = new OrderState(((OrderEvent)event).getAggregateId());
        }

    }

    public void createOrder(OrderId orderId, CustomerId customerId, List<BasketLineItem> basketLineItems) {
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
            derivedEvents.add(new OrderCreated(orderId, customerId, orderLineItems));
        }
    }
}
