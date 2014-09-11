package no.borber.monsterShop.basket;

import java.util.HashMap;
import java.util.Map;

public class BasketState {
    private Map<String, BasketLineItem> basketLineItems = new HashMap<>();
    private BasketId basketId;

    public BasketState(BasketId basketId) {
        this.basketId = basketId;
    }

    public void addItemToBasket(String monsterType) {
        if(!basketLineItems.containsKey(monsterType))
            basketLineItems.put(monsterType, new BasketLineItem(monsterType));
        else
            basketLineItems.get(monsterType).increment();
    }

    public BasketId getBasketId() {
        return basketId;
    }

    public void removeItemFromBasket(String monsterType) {
        if(basketLineItems.containsKey(monsterType)) {
            basketLineItems.get(monsterType).decrement();
            if (basketLineItems.get(monsterType).getCount() == 0)
                basketLineItems.remove(monsterType);
        }
    }
}
