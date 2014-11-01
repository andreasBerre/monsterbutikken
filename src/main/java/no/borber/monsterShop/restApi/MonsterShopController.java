package no.borber.monsterShop.restApi;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public abstract class MonsterShopController {

    @Resource
    private HttpServletRequest httpRequest;

    protected String getCurrentCustomerId() {
        return (String)httpRequest.getSession().getAttribute("currentCustomerId");
    }

    protected void setCurrentCustomerId(String customerId) {
        httpRequest.getSession().setAttribute("currentCustomerId", customerId);
    }

    protected String getCurrentBasketId(){
        return (String)httpRequest.getSession().getAttribute("currentBasketId");
    }

    protected void setCurrentBasketId(String basketId){
        httpRequest.getSession().setAttribute("currentBasketId", basketId);
    }

    protected void removeCurrentBasketId(){
        httpRequest.getSession().removeAttribute("currentBasketId");
    }

    protected void logoutCurrentCustomer() {
        httpRequest.getSession().removeAttribute("currentCustomerId");
    }

}
