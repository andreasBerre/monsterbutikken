package no.borber.serialized;

import no.borber.monsterShop.application.AggregateType;

public class BasketEvent extends Event {

    private final String basketId;

    public BasketEvent(String basketId) {
        this.basketId = basketId;
    }

    @Override
    public String getAggregateId() {
        return basketId;
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.BASKET;
    }
}
