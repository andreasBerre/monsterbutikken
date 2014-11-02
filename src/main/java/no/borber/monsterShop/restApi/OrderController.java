package no.borber.monsterShop.restApi;

import no.borber.monsterShop.application.BasketApplicationService;
import no.borber.monsterShop.application.OrderApplicationService;
import no.borber.monsterShop.readLayer.order.OrderInfo;
import no.borber.monsterShop.readLayer.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class OrderController extends MonsterShopController {

    @Autowired
    Orders orders;

    @Autowired
    BasketApplicationService basketApplicationService;

    @Autowired
    OrderApplicationService orderApplicationService;

    /**
     * Checks out the customers current basket, creating an order based on the basket contents.
     */
    @RequestMapping(value = "/orders",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void checkout(){
        basketApplicationService.checkoutBasket(getCurrentBasketId(), getCurrentCustomerId(), UUID.randomUUID().toString());
        removeCurrentBasketId();
    }

    /**
     * Cancels an order placed by the current customer
     *
     * @param orderId identifier for the order to be canceled
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.DELETE)
    public void cancelOrder(@PathVariable String orderId){
        orderApplicationService.cancelOrder(getCurrentCustomerId(), orderId);
    }

    /**
     * Gets all orders placed by the current customer
     *
     * @return List of orders.
     */
    @RequestMapping(value = "/orders",  method=RequestMethod.GET)
    public List<OrderInfo> getOrders(){
        return orders.getOrders(getCurrentCustomerId());
    }

    /**
     * Gets a single order
     *
     * @param orderId identifier for the order to be retrieved
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.GET)
    public OrderInfo getOrder(@PathVariable String orderId){
        return orders.getOrder(getCurrentCustomerId(), orderId).orElse(null);
    }

}


