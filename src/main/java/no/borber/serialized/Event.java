package no.borber.serialized;

import no.borber.monsterShop.application.AggregateType;

public abstract class Event {

    public abstract String getAggregateId();

    public abstract AggregateType getAggregateType();
}
