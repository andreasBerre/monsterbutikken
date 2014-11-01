package no.borber.monsterShop.basket;

import no.borber.monsterShop.eventStore.CommandValidationException;
import no.borber.serialized.*;

import java.util.List;

public class BasketAggregate extends Aggregate {
    private BasketState basketState;

    public BasketAggregate(List<Event> events) {
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
        } else if (basketState.getBasketCheckedOut()){
            throw new CommandValidationException("Attempt to add monster to basket that has been checked out, add item failed");
        } else {
            basketState.addItemToBasket(monsterType);
            derivedEvents.add(new ItemAddedToBasket(basketState.getBasketId(), monsterType));
        }
    }

    public void removeItemFromBasket(String monsterType) {
        if (basketState == null){
            throw new CommandValidationException("Attempt to remove item from non-existing basket, remove item failed");
        } else if (basketState.getBasketCheckedOut()){
            throw new CommandValidationException("Attempt to remove monster from basket that has been checked out, remove item failed");
        }
        else {
            basketState.removeItemFromBasket(monsterType);
            derivedEvents.add(new ItemRemovedFromBasket(basketState.getBasketId(), monsterType));
        }
    }

    public List<BasketLineItem> checkoutBasket(){
        if (basketState == null){
            throw new CommandValidationException("Attempt to checkout non-existing basket, checkout failed");
        } else if (basketState.getBasketCheckedOut()){
            throw new CommandValidationException("Attempt to checkout basket that has been previously checked out, checkout failed");
        }else {
            basketState.setBasketCheckedOut();
            derivedEvents.add(new BasketCheckedOut(basketState.getBasketId()));
            return basketState.getBasketLineItems();
        }

    }

}
