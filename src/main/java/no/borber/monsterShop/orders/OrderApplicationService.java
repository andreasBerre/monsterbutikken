package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.eventStore.EventStore;

public class OrderApplicationService {
    private final EventStore eventStore;

    public OrderApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void cancelOrder(CustomerId customerId, OrderId orderId) {
        OrderAggregate order = new OrderAggregate(eventStore.getEventsByAggregateId(orderId));
        order.cancelOrder(customerId);

    }
}
