package no.borber.monsterShop.basket;

/**
 * Created by bera on 11.09.14.
 */
public class BasketLineItemJson {
    private final String monsterType;
    private final int count;
    private final double lineItemPrice;

    public BasketLineItemJson(String monsterType, int count, double lineItemPrice) {
        this.monsterType = monsterType;
        this.count = count;
        this.lineItemPrice = lineItemPrice;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getCount() {
        return count;
    }

    public double getLineItemPrice() {
        return lineItemPrice;
    }
}
