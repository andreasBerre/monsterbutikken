package no.borber.serialized;

import no.borber.monsterShop.basket.BasketId;

public class BasketCreated extends BasketEvent{

    public BasketCreated(BasketId id) {
        super(id);
    }

}
