package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.*;

import java.util.HashMap;
import java.util.Map;

public class BasketProjection extends Projection {
    private Map<AggregateId, BasketInfo> baskets = new HashMap<>();

    public BasketProjection(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    protected AggregateType getAggregateType() {
        return AggregateType.BASKET;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof BasketCreated)
            createBasket((BasketCreated) event);
        else if (event instanceof ItemAddedToBasket)
            addItemToBasket((ItemAddedToBasket)event);
        else if (event instanceof ItemRemovedFromBasket)
            removeItemFromBasket((ItemRemovedFromBasket) event);
    }

    private void addItemToBasket(ItemAddedToBasket event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, add item failed");
        baskets.get(event.getAggregateId()).addItem(event.getMonsterType());
    }

    private void removeItemFromBasket(ItemRemovedFromBasket event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, add item failed");
        baskets.get(event.getAggregateId()).removeItem(event.getMonsterType());
    }

    private void createBasket(BasketCreated event) {
        if (baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("Projection already contains the created basket, create basket failed");
        baskets.put(event.getAggregateId(), new BasketInfo(event.getAggregateId()));
    }

    public BasketInfo getBasket(BasketId basketId) {
        return baskets.get(basketId);
    }
}
