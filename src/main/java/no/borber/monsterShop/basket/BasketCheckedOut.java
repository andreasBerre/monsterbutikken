package no.borber.monsterShop.basket;


public class BasketCheckedOut extends BasketEvent {
    public BasketCheckedOut(BasketId basketId) {
        super(basketId);
    }
}
