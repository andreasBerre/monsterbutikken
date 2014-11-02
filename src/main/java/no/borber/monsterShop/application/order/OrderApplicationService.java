package no.borber.monsterShop.application.order;

import no.borber.monsterShop.eventStore.EventStore;

public class OrderApplicationService {
    private final EventStore eventStore;

    public OrderApplicationService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void cancelOrder(String customerId, String orderId) {
        OrderAggregate order = new OrderAggregate(eventStore.getById(orderId));
        order.cancelOrder(customerId);
        eventStore.store(order.getDerivedEvents());
    }
}
