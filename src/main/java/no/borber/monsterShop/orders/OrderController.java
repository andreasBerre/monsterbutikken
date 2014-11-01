package no.borber.monsterShop.orders;

import no.borber.monsterShop.MonsterShopController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController extends MonsterShopController {

    @Autowired
    OrderProjection orderProjection;

    @Autowired
    OrderApplicationService orderApplicationService;

    /**
     * Cancels an order placed by the current customer
     *
     * @param orderId identifier for the order to be canceled
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.DELETE)
    public void cancelOrder(@PathVariable String orderId){
        orderApplicationService.cancelOrder(getCurrentCustomerId(), new OrderId(orderId));
    }

    /**
     * Gets all orders placed by the current customer
     *
     * @return List of orders.
     */
    @RequestMapping(value = "/orders",  method=RequestMethod.GET)
    public List<Order> getOrders(){
        return orderProjection.getOrders(getCurrentCustomerId());
    }

    /**
     * Gets a single order
     *
     * @param orderId identifier for the order to be retrieved
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.GET)
    public Order getOrder(@PathVariable String orderId){
        return orderProjection.getOrder(getCurrentCustomerId(), new OrderId(orderId)).orElse(null);
    }

}


