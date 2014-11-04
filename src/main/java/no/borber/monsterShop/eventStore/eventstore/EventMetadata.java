package no.borber.monsterShop.eventStore.eventstore;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.eventStore.Event;

public class EventMetadata {
    private String eventClass;
    private String aggregateId;
    private String aggregateType;

    public EventMetadata(Class<? extends Event> eventClass, String aggregateId, AggregateType aggregateType) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType.toString();
        this.eventClass = eventClass.getCanonicalName();
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getEventClass() {
        return eventClass;
    }

    public Class<? extends Event> getEventClassAsClass(){
        try {
            return (Class<? extends Event>) Class.forName(eventClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }
}
