package no.borber.monsterShop.projections;

import no.borber.monsterShop.application.basket.events.BasketCreated;
import no.borber.monsterShop.application.basket.events.ItemAddedToBasket;
import no.borber.monsterShop.application.basket.events.ItemRemovedFromBasket;
import no.borber.monsterShop.eventStore.EventStore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class BasketProjectionTest {

    @Test
    public void testItemsAdded() throws Exception {
        BasketProjection basketProjection = new BasketProjection(new EventStore());

        basketProjection.handleEvent(new BasketCreated("id"));
        basketProjection.handleEvent(new ItemAddedToBasket("id", "monster"));

        Collection<BasketLineItemInfo> basketLineItems = basketProjection.getBasketLineItems("id");
        assertEquals(1, basketLineItems.size());
        assertEquals(new BasketLineItemInfo("monster"), basketLineItems.iterator().next());
    }

    @Test
    public void testItemsAddedAndRemoved() throws Exception {
        BasketProjection basketProjection = new BasketProjection(new EventStore());

        basketProjection.handleEvent(new BasketCreated("id"));
        basketProjection.handleEvent(new ItemAddedToBasket("id", "monster"));
        basketProjection.handleEvent(new ItemAddedToBasket("id", "monster"));
        basketProjection.handleEvent(new ItemAddedToBasket("id", "monster"));
        basketProjection.handleEvent(new ItemRemovedFromBasket("id", "monster"));

        Collection<BasketLineItemInfo> basketLineItems = basketProjection.getBasketLineItems("id");
        assertEquals(1, basketLineItems.size());
        BasketLineItemInfo monster = new BasketLineItemInfo("monster");
        monster.incrementQuantity();
        assertEquals(monster, basketLineItems.iterator().next());
    }

}
