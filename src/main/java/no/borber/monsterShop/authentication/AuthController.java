package no.borber.monsterShop.authentication;

import no.borber.monsterShop.MonsterShopController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController extends MonsterShopController {


    @RequestMapping(value= "auth/logIn/{customerName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void loggIn(@PathVariable String customerName){
        setCurrentCustomer(customerName);
    }

    @RequestMapping(value= "auth/logOut", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logOut(){
      logoutCurrentCustomer();
    }

    @RequestMapping(value= "auth/customer", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(){
        return new Customer(getCurrentCustomer());
    }
}
