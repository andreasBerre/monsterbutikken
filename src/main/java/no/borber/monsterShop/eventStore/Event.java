package no.borber.monsterShop.eventStore;

public abstract class Event {

    public abstract AggregateId getAggregateId();

    public abstract AggregateType getAggregateType();
}
