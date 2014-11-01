package no.borber.serialized;

import no.borber.monsterShop.eventStore.AggregateType;

public abstract class Event {

    public abstract AggregateId getAggregateId();

    public abstract AggregateType getAggregateType();
}
