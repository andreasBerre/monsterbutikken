package no.borber.monsterShop.readLayer.basket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BasketInfo {
    private String aggregateId;
    private Map<String, BasketLineItemInfo> basketLineItems = new HashMap<>();

    public BasketInfo(String basketId) {
        this.aggregateId = basketId;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void addItem(String monsterType) {
        if (!basketLineItems.containsKey(monsterType))
            basketLineItems.put(monsterType, new BasketLineItemInfo(monsterType));
        else
            basketLineItems.get(monsterType).incrementQuantity();
    }

    public void removeItem(String monsterType) {
        if (basketLineItems.containsKey(monsterType)) {
            basketLineItems.get(monsterType).decrementQuantity();

            if (basketLineItems.get(monsterType).getQuantity() == 0)
                basketLineItems.remove(monsterType);
        }
    }

    public Collection<BasketLineItemInfo> getLineItems() {
        return basketLineItems.values();
    }
}
