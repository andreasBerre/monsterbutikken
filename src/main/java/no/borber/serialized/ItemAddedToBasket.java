package no.borber.serialized;

import no.borber.monsterShop.basket.BasketId;

public class ItemAddedToBasket extends BasketEvent {
    private final String monsterType;

    public ItemAddedToBasket(BasketId basketId, String monsterType) {
        super(basketId);
        this.monsterType = monsterType;
    }

    public String getMonsterType() {
        return monsterType;
    }
}
