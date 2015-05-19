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

    public String getBasketId() {
        return basketId;
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
