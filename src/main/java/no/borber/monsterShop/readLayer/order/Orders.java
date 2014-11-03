package no.borber.monsterShop.readLayer.order;

import java.util.List;
import java.util.Optional;

public interface Orders {

    List<OrderJson> getOrders(String customerId);

    Optional<OrderJson> getOrder(String customerId, String orderId);
}
