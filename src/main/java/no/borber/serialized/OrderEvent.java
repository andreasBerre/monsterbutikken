package no.borber.serialized;


import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.orders.OrderId;

public class OrderEvent extends Event {

    private final OrderId orderId;

    public OrderEvent(OrderId orderId) {
        this.orderId = orderId;
    }

    @Override
    public OrderId getAggregateId() {
        return orderId;
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.ORDER;
    }
}

