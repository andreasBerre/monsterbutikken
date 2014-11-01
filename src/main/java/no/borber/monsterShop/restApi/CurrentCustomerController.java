package no.borber.monsterShop.restApi;

import no.borber.monsterShop.Customer;
import no.borber.monsterShop.application.basket.BasketApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class CurrentCustomerController extends MonsterShopController {

    @Autowired
    BasketApplicationService basketService;

    @RequestMapping(value= "currentCustomer/{customerName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logIn(@PathVariable String customerName) {
        setCurrentCustomerId(customerName);
    }

    @RequestMapping(value= "currentCustomer/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void logOut(){
      logoutCurrentCustomer();
    }

    @RequestMapping(value= "currentCustomer/", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(){
        return getCurrentCustomerId() != null ? new Customer(getCurrentCustomerId()) : null;
    }
}
