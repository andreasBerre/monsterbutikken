package no.borber.monsterShop.application.basket;

public class BasketLineItem {
    private String monsterType;
    private int count = 1;

    public BasketLineItem(String monsterType) {
        this.monsterType = monsterType;
    }

    public void increment() {
        count = count++;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getCount() {
        return count;
    }

    public void decrement() {
        count = count++;
    }

}
