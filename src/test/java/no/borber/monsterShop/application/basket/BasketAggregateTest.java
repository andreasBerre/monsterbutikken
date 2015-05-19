package no.borber.monsterShop.application.basket;

import no.borber.monsterShop.application.CommandValidationException;
import no.borber.monsterShop.application.basket.events.BasketCheckedOut;
import no.borber.monsterShop.application.basket.events.BasketCreated;
import no.borber.monsterShop.application.basket.events.ItemAddedToBasket;
import no.borber.monsterShop.application.basket.events.ItemRemovedFromBasket;
import no.borber.monsterShop.eventStore.Event;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasketAggregateTest {

    @Test
    public void testCreateBasket() throws Exception {
        BasketAggregate basketAggregate = new BasketAggregate(new ArrayList<>());
        basketAggregate.createBasket("id");
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        assertEquals(1,derivedEvents.size());
        assertEquals(new BasketCreated("id"), derivedEvents.get(0));
    }

    @Test(expected = CommandValidationException.class)
    public void testCreateBasketFailsIfAlreadyCreated() throws Exception {
        BasketAggregate basketAggregate = new BasketAggregate(Arrays.asList(new BasketCreated("id")));
        basketAggregate.createBasket("id");
    }

    @Test
    public void testAddItemToBasket() throws Exception {
        BasketAggregate basketAggregate = new BasketAggregate(Arrays.asList(new BasketCreated("id")));
        basketAggregate.addItemToBasket("DangerousMonster");
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        assertEquals(1,derivedEvents.size());
        assertEquals(new ItemAddedToBasket("id","DangerousMonster"), derivedEvents.get(0));
    }

    @Test(expected = CommandValidationException.class)
    public void testItemAddedToNonExistentBasketFails() throws Exception {
        BasketAggregate basketAggregate = new BasketAggregate(new ArrayList<>());
        basketAggregate.addItemToBasket("monster");
    }

    @Test(expected = CommandValidationException.class)
    public void testItemAddedtoCheckedOutBasketFails() throws Exception {
        BasketAggregate basketAggregate = new BasketAggregate(Arrays.asList(
                new BasketCreated("id"),
                new BasketCheckedOut("id")));
        basketAggregate.addItemToBasket("monster");
    }

    @Test
    public void testItemRemovedFromBasket() throws Exception {
        BasketAggregate basketAggregate = new BasketAggregate(Arrays.asList(
                new BasketCreated("id"),
                new ItemAddedToBasket("id", "monster")));
        basketAggregate.removeItemFromBasket("monster");
        List<Event> derivedEvents = basketAggregate.getDerivedEvents();
        assertEquals(1,derivedEvents.size());
        assertEquals(new ItemRemovedFromBasket("id","monster"), derivedEvents.get(0));
    }

    
}
