package no.borber.monsterShop.projections;

import no.borber.monsterShop.application.AggregateType;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.eventStore.Projection;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderProjection extends Projection{

    public OrderProjection(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    protected AggregateType getSubscribedType() {
        return AggregateType.ORDER;
    }

    @Override
    public void handleEvent(Event event) {
        //TODO: Should build its state from incoming events
    }

    public List<OrderInfo> getOrders(final String customerId) {
        //TODO: Should return a customers order instead of the empty list
        return Collections.EMPTY_LIST;
    }

    public Optional<OrderInfo> getOrder(final String customerId, final String orderId) {
        //TODO: Should return a spesific order
        return null;
    }



}
