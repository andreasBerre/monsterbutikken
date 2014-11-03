package no.borber.monsterShop.readLayer.order;

import java.util.List;

public interface OrderJson {
    String getTimePlaced();

    double getTotal();

    List<OrderLineItemJson> getLineItems();

    String getOrderId();

    boolean isCanceled();
}
