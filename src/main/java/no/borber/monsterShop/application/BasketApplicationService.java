package no.borber.monsterShop.application;

public interface BasketApplicationService {
    void createBasket(String id);

    void addItemToBasket(String id, String monsterType);

    void removeItemFromBasket(String id, String monsterType);

    void checkoutBasket(String basketId, String customerId, String orderId);
}
