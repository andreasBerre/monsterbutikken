package no.borber.monsterShop.application.basket.events;


public class ItemAddedToBasket extends BasketEvent {
    private final String monsterType;

    public ItemAddedToBasket(String basketId, String monsterType) {
        super(basketId);
        this.monsterType = monsterType;
    }

    public String getMonsterType() {
        return monsterType;
    }
}
