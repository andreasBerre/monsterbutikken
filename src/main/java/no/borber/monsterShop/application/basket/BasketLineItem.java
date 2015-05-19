package no.borber.monsterShop.application.basket;

public class BasketLineItem {
    private String monsterType;
    private int quantity = 1;

    public BasketLineItem(String monsterType) {
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
        quantity++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketLineItem that = (BasketLineItem) o;

        if (quantity != that.quantity) return false;
        return !(monsterType != null ? !monsterType.equals(that.monsterType) : that.monsterType != null);

    }

    @Override
    public int hashCode() {
        int result = monsterType != null ? monsterType.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
