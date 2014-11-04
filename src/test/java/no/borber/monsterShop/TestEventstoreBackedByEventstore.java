package no.borber.monsterShop;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.application.basket.events.BasketCheckedOut;
import no.borber.monsterShop.application.basket.events.BasketCreated;
import no.borber.monsterShop.application.basket.events.ItemAddedToBasket;
import no.borber.monsterShop.application.order.OrderLineItem;
import no.borber.monsterShop.application.order.events.OrderCreated;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.EventStoreByGetEventStore;
import no.borber.monsterShop.eventStore.EventStoreProjectionUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TestEventstoreBackedByEventstore {

    @Test
    public void testStoreEvent() throws Exception {
        EventStore eventStore = new EventStoreByGetEventStore();
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new BasketCreated(UUID.randomUUID().toString()));
        eventStore.store(events);
    }

    @Test
    public void testReadEventsByAggregateId() throws Exception {
        EventStore eventStore = new EventStoreByGetEventStore();
        ArrayList<Event> events = new ArrayList<Event>();
        String id = UUID.randomUUID().toString();
        events.add(new BasketCreated(id));
        eventStore.store(events);
        List<Event> eventsById = eventStore.getById(AggregateType.BASKET, id);
        assertEquals(events.get(0), eventsById.get(0));
    }

    @Test
    public void testStoreAndReadTwoEvents() throws Exception {
        EventStore eventStore = new EventStoreByGetEventStore();
        ArrayList<Event> events = new ArrayList<Event>();
        String id = UUID.randomUUID().toString();
        events.add(new BasketCreated(id));
        events.add(new ItemAddedToBasket(id, "trask"));
        eventStore.store(events);
        List<Event> eventsById = eventStore.getById(AggregateType.BASKET, id);
        assertEquals(events.get(0), eventsById.get(0));
        assertEquals(events.get(1), eventsById.get(1));
    }

    @Test
    public void testStore1000eventsAndReadThemBack() throws Exception {
        EventStore eventStore = new EventStoreByGetEventStore();
        ArrayList<Event> events = new ArrayList<Event>();
        String id = UUID.randomUUID().toString();
        events.add(new BasketCreated(id));
        for(int i = 0; i < 1000; i++)
            events.add(new ItemAddedToBasket(id, "trask"));
        eventStore.store(events);
        List<Event> eventsById = eventStore.getById(AggregateType.BASKET, id);
        for(int i= 0; i< 1000; i++) {
            assertEquals(events.get(i), eventsById.get(i));
        }
    }

    @Test
    public void testSubscription() throws Exception {
        EventStoreProjectionUtil.startByCategoryProjection();
        EventStore eventStore = new EventStoreByGetEventStore();
        ArrayList<Event> events = new ArrayList<>();
        final String id = UUID.randomUUID().toString();
        System.out.println(id);
        BasketCreated firstEvent = new BasketCreated(id);
        events.add(firstEvent);
        eventStore.store(events);
        TestProjection.aggregateType = AggregateType.BASKET;
        TestProjection testProjection = new TestProjection(eventStore, id);
        events = new ArrayList<>();
        BasketCheckedOut secondEvent = new BasketCheckedOut(id);
        events.add(secondEvent);
        eventStore.store(events);
        Thread.sleep(500);
        assertEquals(firstEvent, testProjection.events.get(0));
        assertEquals(secondEvent, testProjection.events.get(1));

    }

    @Test
    public void testSubscriptionOnNonExistingStream() throws Exception {
        EventStoreProjectionUtil.startByCategoryProjection();
        EventStore eventStore = new EventStoreByGetEventStore();
        final String id = UUID.randomUUID().toString();
        System.out.println(id);
        TestProjection.aggregateType = AggregateType.BASKET;
        TestProjection testProjection = new TestProjection(eventStore, id);
        ArrayList<Event> events = new ArrayList<>();

        BasketCreated firstEvent = new BasketCreated(id);
        events.add(firstEvent);
        eventStore.store(events);

        Thread.sleep(500);
        assertEquals(firstEvent, testProjection.events.get(0));
    }

    @Test(expected=RuntimeException.class)
    public void testWriteTwoDifferentAggregates() throws Exception {
        EventStore eventStore = new EventStoreByGetEventStore();
        ArrayList<Event> events = new ArrayList<Event>();
        String id = UUID.randomUUID().toString();
        events.add(new BasketCreated(id));
        events.add(new ItemAddedToBasket(id, "trask"));
        OrderCreated orderCreated = new OrderCreated(UUID.randomUUID().toString(), "idar", new ArrayList<OrderLineItem>(), LocalDateTime.now());
        events.add(orderCreated);
        eventStore.store(events);
    }
}
