package no.borber.monsterShop.application.order.events;


import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.eventStore.Event;

public class OrderEvent extends Event {

    private final String orderId;

    public OrderEvent(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getAggregateId() {
        return orderId;
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.ORDER;
    }
}

