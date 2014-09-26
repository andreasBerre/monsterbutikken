package no.borber.monsterShop;

import no.borber.monsterShop.authentication.Customer;
import no.borber.monsterShop.authentication.CustomerId;
import no.borber.monsterShop.basket.BasketId;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public abstract class MonsterShopController {

    @Resource
    private HttpServletRequest httpRequest;

    protected CustomerId getCurrentCustomerId() {
        return (CustomerId)httpRequest.getSession().getAttribute("customerName");
    }

    protected void setCurrentCustomerId(CustomerId customerName) {
        httpRequest.getSession().setAttribute("customerName", customerName);
    }

    protected BasketId getCurrentBasketId(){
        return (BasketId)httpRequest.getSession().getAttribute("currentBasketId");
    }

    protected void setCurrentBasketId(BasketId basketId){
        httpRequest.getSession().setAttribute("currentBasketId", basketId);
    }

    protected void logoutCurrentCustomer() {
        httpRequest.getSession().removeAttribute("customerName");
    }

}
