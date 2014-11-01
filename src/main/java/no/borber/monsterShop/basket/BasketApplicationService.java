package no.borber.monsterShop.basket;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.orders.OrderAggregate;
import no.borber.monsterShop.orders.OrderId;
import no.borber.serialized.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BasketApplicationService {

    private EventStore eventStore;

    public BasketApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void createBasket(BasketId id) {
        BasketAggregate basketAggregate = new BasketAggregate(eventStore.getEventsByAggregateId(id));
        basketAggregate.createBasket(id);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void addItemToBasket(BasketId id, String monsterType) {
        BasketAggregate basketAggregate = new BasketAggregate(eventStore.getEventsByAggregateId(id));
        basketAggregate.addItemToBasket(monsterType);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void removeItemFromBasket(BasketId id, String monsterType) {
        BasketAggregate basketAggregate = new BasketAggregate(eventStore.getEventsByAggregateId(id));
        basketAggregate.removeItemFromBasket(monsterType);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void checkoutBasket(BasketId basketId, CustomerId customerId, OrderId orderId) {
        BasketAggregate basket = new BasketAggregate(eventStore.getEventsByAggregateId(basketId));
        List<BasketLineItem> currentBasketLineItems = basket.checkoutBasket();

        OrderAggregate order = new OrderAggregate(eventStore.getEventsByAggregateId(orderId));
        order.createOrder(orderId, customerId, currentBasketLineItems, LocalDateTime.now());

        List<Event> derivedEvents = new ArrayList<>();
        derivedEvents.addAll(basket.getDerivedEvents());
        derivedEvents.addAll(order.getDerivedEvents());

        eventStore.storeEvents(derivedEvents);
    }
}
