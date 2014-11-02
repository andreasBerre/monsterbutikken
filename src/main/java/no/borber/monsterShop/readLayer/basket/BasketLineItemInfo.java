package no.borber.monsterShop.readLayer.basket;

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
}
