package no.borber.monsterShop.projections;

public class OrderLineItemInfo {
    private final String monsterType;
    private final int quantity;
    private final double unitPrice;

    public OrderLineItemInfo(String monsterType, int quantity, double unitPrice) {
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
