package no.borber.monsterShop.application;

import no.borber.serialized.Event;

import java.util.ArrayList;
import java.util.List;

public class Aggregate {
    protected List<Event> derivedEvents = new ArrayList<>();

    public List<Event> getDerivedEvents() {
        return derivedEvents;
    }
}
