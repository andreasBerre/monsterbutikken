package no.borber.monsterShop.readLayer.order;

import java.util.List;
import java.util.Optional;

public interface Orders {

    List<OrderInfo> getOrders(String customerId);

    Optional<OrderInfo> getOrder(String customerId, String orderId);
}
