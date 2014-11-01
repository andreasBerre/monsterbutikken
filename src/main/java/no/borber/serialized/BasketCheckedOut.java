package no.borber.serialized;


import no.borber.monsterShop.basket.BasketId;

public class BasketCheckedOut extends BasketEvent {
    public BasketCheckedOut(BasketId basketId) {
        super(basketId);
    }
}
