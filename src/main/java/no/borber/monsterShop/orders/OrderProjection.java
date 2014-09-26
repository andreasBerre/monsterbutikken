package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.eventStore.EventStore;

import java.util.Map;

public class OrderProjection {
    public OrderProjection(EventStore eventStore) {

    }

    public Map<String, Order> getOrders(CustomerId currentCustomerId) {
        return null;
    }
}
