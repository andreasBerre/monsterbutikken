package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;

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

    public void placeOrder(BasketId basketId) {
        List<Event> events = eventStore.getEventsByAggregateId(basketId);
        BasketAggregate basket = new BasketAggregate(events);
        basket.generateOrder();
        List<Event> derivedEvents = basket.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }
}
