package no.borber.monsterShop.application.basket;

import no.borber.monsterShop.application.basket.events.BasketCreated;
import no.borber.monsterShop.application.basket.events.ItemAddedToBasket;
import no.borber.monsterShop.application.basket.events.ItemRemovedFromBasket;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasketApplicationServiceTest {

    @Test
    public void testCreateBasket() throws Exception {
        EventStore eventStore = new EventStore();
        BasketApplicationService basketApplicationService = new BasketApplicationService(eventStore);
        basketApplicationService.createBasket("id");
        assertEquals(1, eventStore.getById("id").size());
        assertEquals(new BasketCreated("id"), eventStore.getById("id").get(0));
    }

    @Test
    public void testAddItemToBasket() throws Exception {
        EventStore eventStore = new EventStore();
        BasketApplicationService basketApplicationService = new BasketApplicationService(eventStore);
        basketApplicationService.createBasket("id");
        basketApplicationService.addItemToBasket("id","monster");
        List<Event> events = eventStore.getById("id");
        assertEquals(2, events.size());
        assertEquals(new ItemAddedToBasket("id", "monster"), events.get(1));
    }

    @Test
    public void testRemoveItemFromBasket() throws Exception {
        EventStore eventStore = new EventStore();
        BasketApplicationService basketApplicationService = new BasketApplicationService(eventStore);
        basketApplicationService.createBasket("id");
        basketApplicationService.addItemToBasket("id","monster");
        basketApplicationService.removeItemFromBasket("id", "monster");
        List<Event> events = eventStore.getById("id");
        assertEquals(3, events.size());
        assertEquals(new ItemRemovedFromBasket("id", "monster"), events.get(2));
    }
}
