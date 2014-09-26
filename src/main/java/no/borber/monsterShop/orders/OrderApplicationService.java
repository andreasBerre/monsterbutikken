package no.borber.monsterShop.orders;

import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.basket.BasketAggregate;
import no.borber.monsterShop.basket.BasketId;
import no.borber.monsterShop.eventStore.Event;
import no.borber.monsterShop.eventStore.EventStore;

import java.util.List;

public class OrderApplicationService {
    private final EventStore eventStore;

    public OrderApplicationService(EventStore eventStore) {
            this.eventStore = eventStore;
    }



}
