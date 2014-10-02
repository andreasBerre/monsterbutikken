package no.borber.monsterShop.orders;

import no.borber.monsterShop.MonsterShopController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class OrderController extends MonsterShopController {

    @Autowired
    OrderProjection orderProjection;

    /**
     * Gets all orders placed by the current customer
     *
     * @return Map of orders, where the key is the order-id and the value an order object.
     */
    @RequestMapping(value = "/orders",  method=RequestMethod.GET)
    @ResponseBody()
    public Map<String, Order> getOrders(){
        return orderProjection.getOrders(getCurrentCustomerId());
    }

    /**
     * Gets a single order
     *
     * @param orderId identifier for the order to be retrieved
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.GET)
    @ResponseBody()
    public Order getOrder(@PathVariable String orderId){
        return orderProjection.getOrder(getCurrentCustomerId(), orderId);
    }

}


