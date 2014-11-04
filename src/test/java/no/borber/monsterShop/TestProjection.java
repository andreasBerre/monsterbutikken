package no.borber.monsterShop;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;

import java.util.ArrayList;
import java.util.List;

public class TestProjection extends Projection {

    private final String id;
    public static AggregateType aggregateType;

    public TestProjection(EventStore eventStore, String id) {
        super(eventStore);
        this.id = id;
    }
    public List<Event> events = new ArrayList<>();

    @Override
    protected AggregateType getSubscribedType() {
        return aggregateType;
    }

    @Override
    public void handleEvent(Event event) {
        if(id.equals(event.getAggregateId()))
            events.add(event);
    }
}
