package no.borber.monsterShop.application.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BasketState {
    private Map<String, BasketLineItem> basketLineItems = new HashMap<>();
    private String basketId;
    private boolean basketCheckedOut = false;

    BasketState(String basketId) {
        this.basketId = basketId;
    }

    public void addItemToBasket(String monsterType) {
        if(!basketLineItems.containsKey(monsterType))
            basketLineItems.put(monsterType, new BasketLineItem(monsterType));
        else
            basketLineItems.get(monsterType).incrementQuantity();
    }

    public String getBasketId() {
        return basketId;
    }

    public void removeItemFromBasket(String monsterType) {
        if(basketLineItems.containsKey(monsterType)) {
            basketLineItems.get(monsterType).decrementQuantity();
            if (basketLineItems.get(monsterType).getQuantity() == 0)
                basketLineItems.remove(monsterType);
        }
    }

    public void setBasketCheckedOut() {
        basketCheckedOut = true;
    }

    public boolean getBasketCheckedOut() {
        return basketCheckedOut;
    }

    public List<BasketLineItem> getBasketLineItems() {
        return new ArrayList<>(basketLineItems.values());
    }

}
