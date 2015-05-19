package no.borber.monsterShop.application.basket;

import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;

import java.util.ArrayList;
import java.util.List;

public class AssertingEventStore extends EventStore {
    public AssertingEventStore() {
        super();
    }

    List<Event> storedEvents = new ArrayList<>();
    @Override
    public void store(List<Event> eventsToSave) {
        storedEvents.addAll(eventsToSave);
    }

    public List<Event> getStoredEvents() {
        return storedEvents;
    }
}
