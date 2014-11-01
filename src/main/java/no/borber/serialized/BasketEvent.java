package no.borber.serialized;

import no.borber.monsterShop.basket.BasketId;
import no.borber.monsterShop.eventStore.AggregateType;

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
