package no.borber.monsterShop.projections;

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
            basketLineItems.get(monsterType).increment();
    }

    public void removeItem(String monsterType) {
        if (basketLineItems.containsKey(monsterType)) {
            basketLineItems.get(monsterType).decrement();

            if (basketLineItems.get(monsterType).getCount() == 0)
                basketLineItems.remove(monsterType);
        }
    }

    public Collection<BasketLineItemInfo> getBasketLineItems() {
        return basketLineItems.values();
    }
}
