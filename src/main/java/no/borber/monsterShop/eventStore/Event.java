package no.borber.monsterShop.eventStore;

import no.borber.monsterShop.application.AggregateType;

public abstract class Event {

    public abstract String getAggregateId();

    public abstract AggregateType getAggregateType();

}
