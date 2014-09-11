package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.AggregateId;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BasketInfo {
    private AggregateId aggregateId;
    private Map<String, BasketLineItem> basketLineItems = new HashMap<>();

    public BasketInfo(BasketId basketId) {
        this.aggregateId = basketId;
    }

    public AggregateId getAggregateId() {
        return aggregateId;
    }

    public void addItem(String monsterType) {
        if (!basketLineItems.containsKey(monsterType))
        basketLineItems.put(monsterType, new BasketLineItem(monsterType));

        basketLineItems.get(monsterType).increment();
    }

    public void removeItem(String monsterType) {
        if (basketLineItems.containsKey(monsterType)) {
            basketLineItems.get(monsterType).decrement();

            if (basketLineItems.get(monsterType).getCount() == 0)
                basketLineItems.remove(monsterType);
        }
    }

    public Collection<BasketLineItem> getBasketLineItems() {
        return basketLineItems.values();
    }
}
