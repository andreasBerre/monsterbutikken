package no.borber.monsterShop.application.order;

import no.borber.monsterShop.application.Aggregate;
import no.borber.monsterShop.application.basket.BasketLineItem;
import no.borber.monsterShop.eventStore.Event;

import java.time.LocalDateTime;
import java.util.List;

public class OrderAggregate extends Aggregate {

    public OrderAggregate(List<Event> events) {
        //TODO: Should build itself using events from the store
    }

    public void createOrder(String orderId, String customerId, List<BasketLineItem> basketLineItems, LocalDateTime orderTime) {
        //TODO: Needs to handle this command
    }
}
