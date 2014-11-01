package no.borber.monsterShop.projections;

public class BasketLineItemInfo {
    private final String monsterType;
    private int count = 1;

    public BasketLineItemInfo(String monsterType) {
        this.monsterType = monsterType;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }
}
