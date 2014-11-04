package no.borber.monsterShop.eventStore;

import no.borber.monsterShop.application.AggregateType;

import java.util.List;

/**
 * Created by idar on 11/2/14.
 */
public interface EventStore {
    void store(List<Event> eventsToSave);

    List<Event> getById(AggregateType aggregateType, String id);

    void subscribe(AggregateType aggregateType, Projection projection);
}
