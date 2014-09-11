package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.monsterShop.eventStore.Event;

public class BasketEvent extends Event {

    private final BasketId basketId;

    public BasketEvent(BasketId basketId) {
        this.basketId = basketId;
    }

    @Override
    public BasketId getAggregateId() {
        return basketId;
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.BASKET;
    }
}
