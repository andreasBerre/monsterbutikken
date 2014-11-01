package no.borber.serialized;

public class ItemRemovedFromBasket extends BasketEvent {
    private final String monsterType;

    public ItemRemovedFromBasket(String basketId, String monsterType) {
        super(basketId);
        this.monsterType = monsterType;
    }

    public String getMonsterType() {
        return monsterType;
    }
}
