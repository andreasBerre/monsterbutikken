package no.borber.monsterShop.projections;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.application.basket.events.BasketCheckedOut;
import no.borber.monsterShop.application.basket.events.BasketCreated;
import no.borber.monsterShop.application.basket.events.ItemAddedToBasket;
import no.borber.monsterShop.application.basket.events.ItemRemovedFromBasket;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BasketProjection extends Projection {
    private Map<String, BasketInfo> baskets = new HashMap<>();

    public BasketProjection(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    protected AggregateType getSubscribedType() {
        return AggregateType.BASKET;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof BasketCreated)
            handleBasketCreated((BasketCreated) event);
        else if (event instanceof ItemAddedToBasket)
            handleItemAddedToBasket((ItemAddedToBasket) event);
        else if (event instanceof ItemRemovedFromBasket)
            handleItemRemovedFromBasket((ItemRemovedFromBasket) event);
        else if (event instanceof BasketCheckedOut)
            handleBasketCheckedOut((BasketCheckedOut) event);
    }

    public Collection<BasketLineItemInfo> getBasketLineItems(String basketId) {
        return baskets.get(basketId) != null ? baskets.get(basketId).getLineItems() : Collections.<BasketLineItemInfo>emptyList();
    }

    private void handleBasketCreated(BasketCreated event) {
        if (baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("Projection already contains the created basket, create basket failed");
        baskets.put(event.getAggregateId(), new BasketInfo(event.getAggregateId()));
    }

    private void handleItemAddedToBasket(ItemAddedToBasket event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, add item failed");
        baskets.get(event.getAggregateId()).addItem(event.getMonsterType());
    }

    private void handleItemRemovedFromBasket(ItemRemovedFromBasket event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, add item failed");
        baskets.get(event.getAggregateId()).removeItem(event.getMonsterType());
    }

    private void handleBasketCheckedOut(BasketCheckedOut event) {
        if (!baskets.containsKey(event.getAggregateId()))
            throw new RuntimeException("The projection does not contain the specified basket, checkout failed");
        baskets.remove(event.getAggregateId());
    }
}
