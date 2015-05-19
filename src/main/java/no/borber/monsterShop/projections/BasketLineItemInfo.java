package no.borber.monsterShop.projections;

public class BasketLineItemInfo {
    private final String monsterType;
    private int quantity = 1;

    public BasketLineItemInfo(String monsterType) {
        this.monsterType = monsterType;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketLineItemInfo that = (BasketLineItemInfo) o;

        if (quantity != that.quantity) return false;
        return !(monsterType != null ? !monsterType.equals(that.monsterType) : that.monsterType != null);

    }

    @Override
    public int hashCode() {
        int result = monsterType != null ? monsterType.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "BasketLineItemInfo{" +
                "monsterType='" + monsterType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
