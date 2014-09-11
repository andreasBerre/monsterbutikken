package no.borber.monsterShop.eventStore;

public abstract class Projection {

    public Projection(EventStore eventStore) {
        eventStore.subscribe(getAggregateType(), this);
    }

    protected abstract AggregateType getAggregateType();

    public abstract void handleEvent(Event event);
}
