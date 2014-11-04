package no.borber.monsterShop.application.basket;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.application.order.OrderAggregate;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BasketApplicationService {

    private EventStore eventStore;

    public BasketApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void createBasket(String id) {
        BasketAggregate basketAggregate = new BasketAggregate(eventStore.getById(AggregateType.BASKET, id));
        basketAggregate.createBasket(id);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.store(derivedEvents);
    }

    public void addItemToBasket(String id, String monsterType) {
        BasketAggregate basketAggregate = new BasketAggregate(eventStore.getById(AggregateType.BASKET, id));
        basketAggregate.addItemToBasket(monsterType);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.store(derivedEvents);
    }

    public void removeItemFromBasket(String id, String monsterType) {
        BasketAggregate basketAggregate = new BasketAggregate(eventStore.getById(AggregateType.BASKET, id));
        basketAggregate.removeItemFromBasket(monsterType);
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        eventStore.store(derivedEvents);
    }

    public void checkoutBasket(String basketId, String customerId, String orderId) {
        BasketAggregate basket = new BasketAggregate(eventStore.getById(AggregateType.BASKET, basketId));
        List<BasketLineItem> currentBasketLineItems = basket.checkoutBasket();

        OrderAggregate order = new OrderAggregate(eventStore.getById(AggregateType.ORDER, orderId));
        order.createOrder(orderId, customerId, currentBasketLineItems, LocalDateTime.now());

        eventStore.store(basket.getDerivedEvents());
        eventStore.store(order.getDerivedEvents());
    }
}
