package no.borber.monsterShop.eventStore;

import no.borber.monsterShop.application.AggregateType;
import no.borber.serialized.Event;

public abstract class Projection {

    public Projection(EventStore eventStore) {
        eventStore.subscribe(getAggregateType(), this);
    }

    protected abstract AggregateType getAggregateType();

    public abstract void handleEvent(Event event);
}
