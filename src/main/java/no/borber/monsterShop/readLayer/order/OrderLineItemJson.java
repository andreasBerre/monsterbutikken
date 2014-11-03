package no.borber.monsterShop.readLayer.order;

public interface OrderLineItemJson {
    String getMonsterType();

    int getQuantity();

    double getUnitPrice();
}