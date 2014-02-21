package no.borber.monsterShop.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Resource
    private HttpServletRequest httpRequest;

    @RequestMapping(value= "auth/logIn/{customerName}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void loggIn(@PathVariable String customerName){
        httpRequest.getSession().setAttribute("customerName", customerName);
    }

    @RequestMapping(value= "auth/logOut", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logOut(){
        httpRequest.getSession().removeAttribute("customerName");
    }

    @RequestMapping(value= "auth/customer", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(){
        return new Customer((String) httpRequest.getSession().getAttribute("customerName"));
    }
}
