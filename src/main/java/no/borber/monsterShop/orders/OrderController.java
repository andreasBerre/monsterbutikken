package no.borber.monsterShop.orders;

import no.borber.monsterShop.MonsterShopController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class OrderController extends MonsterShopController {

    /**
     * Gets the current customers orders
     *
     * @return Map of orders, where the key is the order-id and the value an order object.
     */
    @RequestMapping(value = "/orders",  method=RequestMethod.GET)
    @ResponseBody()
    public Map<String, Order> getOrders(){
        return null;
    }

    /**
     * Gets a single order
     *
     * @param orderId identifier for the order to be retrieved
     */
    @RequestMapping(value = "/orders/{orderId}",  method=RequestMethod.GET)
    @ResponseBody()
    public Order getOrder(@PathVariable String orderId){
        return null;
    }

    /**
     * Submits a new order for the current customer
     *
     */
    @RequestMapping(value = "/orders/placeOrder",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void placeOrder(){}
}


