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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketEvent that = (BasketEvent) o;

        if (basketId != null ? !basketId.equals(that.basketId) : that.basketId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return basketId != null ? basketId.hashCode() : 0;
    }
}
