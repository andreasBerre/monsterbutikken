package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.CommandValidationException;
import no.borber.monsterShop.eventStore.Event;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private BasketState basketState;
    private List<Event> derivedEvents = new ArrayList<>();

    public Basket(List<Event> events) {
        for (Event event : events) {
            if (event instanceof BasketCreated)
                basketState = new BasketState((BasketId) event.getAggregateId());
            else if (event instanceof ItemAddedToBasket)
                basketState.addItemToBasket(((ItemAddedToBasket) event).getMonsterType());
            else if (event instanceof ItemRemovedFromBasket)
                basketState.removeItemFromBasket(((ItemRemovedFromBasket) event).getMonsterType());
        }
    }

    public void createBasket(BasketId id) throws CommandValidationException {
        if (basketState != null) {
            throw new CommandValidationException("Attempt to create basket with pre-existing id, create basket failed");
        } else {
            BasketCreated basketCreated = new BasketCreated(id);
            basketState = new BasketState((basketCreated).getAggregateId());
            derivedEvents.add(basketCreated);
        }
    }

    public void addItemToBasket(String monsterType) {
        if (basketState == null){
            throw new CommandValidationException("Attempt to add item to non-existing basket, add item failed");
        } else {
            basketState.addItemToBasket(monsterType);
            derivedEvents.add(new ItemAddedToBasket(basketState.getBasketId(), monsterType));
        }
    }

    public void removeItemFromBasket(String monsterType) {
        if (basketState == null){
            throw new CommandValidationException("Attempt to remove item from non-existing basket, remove item failed");
        } else {
            basketState.removeItemFromBasket(monsterType);
            derivedEvents.add(new ItemRemovedFromBasket(basketState.getBasketId(), monsterType));
        }
    }

    public List<Event> getDerivedEvents() {
        return derivedEvents;
    }
}
