package no.borber.monsterShop.orders;

public class OrderLineItem {
    private final String monsterType;
    private final int count;
    private final double monsterTypePrice;

    public OrderLineItem(String monsterType, int count, double monsterTypePrice) {
        this.monsterType = monsterType;
        this.count = count;
        this.monsterTypePrice = monsterTypePrice;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getCount() {
        return count;
    }

    public double getMonsterTypePrice() {
        return monsterTypePrice;
    }
}
