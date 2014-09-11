package no.borber.monsterShop.basket;

public class ItemRemovedFromBasket extends BasketEvent {
    private final String monsterType;

    public ItemRemovedFromBasket(BasketId basketId, String monsterType) {
        super(basketId);
        this.monsterType = monsterType;
    }

    public String getMonsterType() {
        return monsterType;
    }
}
