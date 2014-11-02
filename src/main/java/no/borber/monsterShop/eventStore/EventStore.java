package no.borber.monsterShop.eventStore;

import no.borber.monsterShop.application.AggregateType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventStore {
    private List<Event> events = new ArrayList<>();
    private Map<AggregateType, List<Projection>> subscribers = new HashMap<>();

    public void store(List<Event> eventsToSave) {
        events.addAll(eventsToSave);
        eventsToSave.stream().forEach(this::publish);
    }

    public List<Event> getById(String id) {
        return events.stream()
                .filter(event -> event.getAggregateId().equals(id))
                .collect(Collectors.toList());
    }

    private void publish(Event event) {
        subscribers.get(event.getAggregateType()).stream()
                .forEach(s -> s.handleEvent(event));
    }

    public void subscribe(AggregateType aggregateType, Projection projection) {
        if (subscribers.get(aggregateType) == null)
            subscribers.put(aggregateType, new ArrayList<>());

        subscribers.get(aggregateType).add(projection);
    }
}
