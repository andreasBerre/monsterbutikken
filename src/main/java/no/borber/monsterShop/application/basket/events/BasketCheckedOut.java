package no.borber.monsterShop.application.basket.events;


public class BasketCheckedOut extends BasketEvent {
    public BasketCheckedOut(String basketId) {
        super(basketId);
    }
}
