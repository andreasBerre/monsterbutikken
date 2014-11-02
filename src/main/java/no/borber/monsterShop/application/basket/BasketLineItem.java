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

}
