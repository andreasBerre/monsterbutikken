package no.borber.serialized;


import no.borber.monsterShop.application.AggregateType;

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

