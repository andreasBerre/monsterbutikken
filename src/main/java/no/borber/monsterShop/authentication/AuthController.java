package no.borber.monsterShop.authentication;

import no.borber.monsterShop.MonsterShopController;
import no.borber.monsterShop.basket.BasketApplicationService;
import no.borber.monsterShop.basket.BasketId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class AuthController extends MonsterShopController {

    @Autowired
    BasketApplicationService basketService;

    @RequestMapping(value= "auth/logIn/{customerName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logIn(@PathVariable String customerName) {
        setCurrentCustomerId(new CustomerId(customerName));
        if (getCurrentBasketId() == null){
            BasketId basketId = new BasketId(UUID.randomUUID().toString());
            basketService.createBasket(basketId);
            setCurrentBasketId(basketId);
        }
    }

    @RequestMapping(value= "auth/logOut", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logOut(){
      logoutCurrentCustomer();
    }

    @RequestMapping(value= "auth/customer", method = RequestMethod.GET)
    public Customer getCustomer(){
        return getCurrentCustomerId() != null ? new Customer(getCurrentCustomerId().toString()) : null;
    }
}
