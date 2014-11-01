package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.AggregateType;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;
import no.borber.serialized.*;

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
            handleCreateBasket((BasketCreated) event);
        else if (event instanceof ItemAddedToBasket)
            handleAddItemToBasket((ItemAddedToBasket) event);
        else if (event instanceof ItemRemovedFromBasket)
            handleRemoveItemFromBasket((ItemRemovedFromBasket) event);
    }

    private void handleAddItemToBasket(ItemAddedToBasket event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, add item failed");
        baskets.get(event.getAggregateId()).addItem(event.getMonsterType());
    }

    private void handleRemoveItemFromBasket(ItemRemovedFromBasket event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, add item failed");
        baskets.get(event.getAggregateId()).removeItem(event.getMonsterType());
    }

    private void handleCreateBasket(BasketCreated event) {
        if (baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("Projection already contains the created basket, create basket failed");
        baskets.put(event.getAggregateId(), new BasketInfo(event.getAggregateId()));
    }

    public BasketInfo getBasket(BasketId basketId) {
        return baskets.get(basketId);
    }
}
