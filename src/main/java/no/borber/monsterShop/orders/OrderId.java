package no.borber.monsterShop.orders;

import no.borber.serialized.AggregateId;

public class OrderId extends AggregateId{
    public OrderId(String aggregateId) {
        super(aggregateId);
    }
}
