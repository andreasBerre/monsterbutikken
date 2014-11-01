package no.borber.monsterShop.restApi;

import no.borber.monsterShop.application.basket.BasketApplicationService;
import no.borber.monsterShop.application.order.OrderApplicationService;
import no.borber.monsterShop.projections.OrderInfo;
import no.borber.monsterShop.projections.OrderProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class OrderController extends MonsterShopController {

    @Autowired
    OrderProjection orderProjection;

    @Autowired
    BasketApplicationService basketApplicationService;

    @Autowired
    OrderApplicationService orderApplicationService;

    /**
     * Checks out the customers current basket and creates an order based on the basket contents.
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
        return orderProjection.getOrders(getCurrentCustomerId());
    }

    /**
     * Gets a single order
     *
     * @param orderId identifier for the order to be retrieved
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.GET)
    public OrderInfo getOrder(@PathVariable String orderId){
        return orderProjection.getOrder(getCurrentCustomerId(), orderId).orElse(null);
    }

}


