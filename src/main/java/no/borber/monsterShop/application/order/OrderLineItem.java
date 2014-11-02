package no.borber.monsterShop.application.order;

public class OrderLineItem {
    private final String monsterType;
    private final int quantity;
    private final double unitPrice;

    public OrderLineItem(String monsterType, int quantity, double unitPrice) {
        this.monsterType = monsterType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
