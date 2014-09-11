package no.borber.monsterShop.basket;

public class BasketLineItem {
    private String monsterType;
    private int count;

    public BasketLineItem(String monsterType) {
        this.monsterType = monsterType;
    }

    public void increment() {
        this.count++;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getCount() {
        return count;
    }

    public void decrement() {
        count--;
    }

}
