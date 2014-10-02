package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.AggregateId;

public class OrderId extends AggregateId{
    protected OrderId(String aggregateId) {
        super(aggregateId);
    }
}
