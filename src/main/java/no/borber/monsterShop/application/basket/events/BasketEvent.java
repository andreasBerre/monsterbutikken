package no.borber.monsterShop.application.basket.events;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.eventStore.Event;

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
