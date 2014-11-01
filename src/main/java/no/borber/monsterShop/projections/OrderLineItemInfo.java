package no.borber.monsterShop.projections;

public class OrderLineItemInfo {
    private final String monsterType;
    private final int count;
    private final double monsterTypePrice;

    public OrderLineItemInfo(String monsterType, int count, double monsterTypePrice) {
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
