package no.borber.monsterShop.orders;


import no.borber.monsterShop.basket.OrderId;
import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.monsterShop.eventStore.Event;

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

