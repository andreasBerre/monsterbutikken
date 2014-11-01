package no.borber.serialized;


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
