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
        Basket basket = new Basket(events);
        basket.createBasket(id);
        List<Event> derivedEvents = basket.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void addItemToBasket(BasketId id, String monsterType) {
        List<Event> events = eventStore.getEventsByAggregateId(id);
        Basket basket = new Basket(events);
        basket.addItemToBasket(monsterType);
        List<Event> derivedEvents = basket.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }

    public void removeItemFromBasket(BasketId id, String monsterType) {
        List<Event> events = eventStore.getEventsByAggregateId(id);
        Basket basket = new Basket(events);
        basket.removeItemFromBasket(monsterType);
        List<Event> derivedEvents = basket.getDerivedEvents();
        eventStore.storeEvents(derivedEvents);
    }
}
