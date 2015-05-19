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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ItemAddedToBasket that = (ItemAddedToBasket) o;

        return !(monsterType != null ? !monsterType.equals(that.monsterType) : that.monsterType != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (monsterType != null ? monsterType.hashCode() : 0);
        return result;
    }
}
