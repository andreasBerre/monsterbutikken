package no.borber.serialized;

import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.serialized.AggregateId;

public abstract class Event {

    public abstract AggregateId getAggregateId();

    public abstract AggregateType getAggregateType();
}
