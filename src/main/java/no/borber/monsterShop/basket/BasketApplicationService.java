package no.borber.monsterShop.basket;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.orders.OrderAggregate;

import java.util.ArrayList;
import java.util.List;

public class BasketApplicationService {

    private EventStore eventStore;

    public BasketApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void createBasket(BasketId id) {
        List<Event> events = eventStore.getEventsByAggregateId(id);
        BasketAggregate basketAggregate = new BasketAggregate(events);
        basketAggregate.createBasket(id);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void addItemToBasket(BasketId id, String monsterType) {
        List<Event> events = eventStore.getEventsByAggregateId(id);
        BasketAggregate basketAggregate = new BasketAggregate(events);
        basketAggregate.addItemToBasket(monsterType);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void removeItemFromBasket(BasketId id, String monsterType) {
        List<Event> events = eventStore.getEventsByAggregateId(id);
        BasketAggregate basketAggregate = new BasketAggregate(events);
        basketAggregate.removeItemFromBasket(monsterType);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void checkoutBasket(BasketId basketId, CustomerId customerId, OrderId orderId) {
        List<Event> basketEvents = eventStore.getEventsByAggregateId(basketId);
        BasketAggregate basket = new BasketAggregate(basketEvents);
        List<BasketLineItem> currentBasketLineItems = basket.checkoutBasket();

        List<Event> orderEvents = eventStore.getEventsByAggregateId(orderId);
        OrderAggregate order = new OrderAggregate(orderEvents);
        order.createOrder(orderId, customerId, currentBasketLineItems);

        List<Event> derivedEvents = new ArrayList<>();
        derivedEvents.addAll(basket.getDerivedEvents());
        derivedEvents.addAll(order.getDerivedEvents());

        eventStore.storeEvents(derivedEvents);
    }
}
