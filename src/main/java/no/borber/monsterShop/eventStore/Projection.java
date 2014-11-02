package no.borber.monsterShop.eventStore;

import no.borber.monsterShop.application.AggregateType;

public abstract class Projection {

    public Projection(EventStore eventStore) {
        eventStore.subscribe(getSubscribedType(), this);
    }

    protected abstract AggregateType getSubscribedType();

    public abstract void handleEvent(Event event);
}
