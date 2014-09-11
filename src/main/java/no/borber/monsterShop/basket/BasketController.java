package no.borber.monsterShop.basket;

import no.borber.monsterShop.MonsterShopController;
import no.borber.monsterShop.monsterTypes.MonsterTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Controller
public class BasketController extends MonsterShopController{

    @Autowired
    BasketApplicationService basketService;

    @Autowired
    BasketProjection basketProjection;

    /**
     * Gets the current state of a customers basket
     *
     * @return Map of String monsterType og basketItem for the applicable monster type.
     */
    @RequestMapping(value = "/basket/",  method=RequestMethod.GET)
    @ResponseBody()
    public Map<String, BasketLineItemJson> getBasket(){
        return getBasketJson();
    }

    private Map<String, BasketLineItemJson> getBasketJson() {
        Map<String, BasketLineItemJson> basketLineItemsJson = new HashMap<>();

        Collection<BasketLineItem> lineItems = basketProjection.getBasket(getCurrentBasketId()).getBasketLineItems();
        for (BasketLineItem item : lineItems) {
            double lineItemPrice = MonsterTypesRepo.getMonsterType(item.getMonsterType()).getPrice() * item.getCount();
            basketLineItemsJson.put(item.getMonsterType(), new BasketLineItemJson(item.getMonsterType(), item.getCount(), lineItemPrice));
        }
        return basketLineItemsJson;
    }

    /**
     * Adds a new monster of a specified type to the customers basket. If there is an existing basket item the number
     * of monsters is incremented, otherwise a new order baslet item is created.
     *
     * @param monstertype name of the monstertype to be added
     */
    @RequestMapping(value = "/basket/{monstertype}",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void add(@PathVariable String monstertype){
        basketService.addItemToBasket(getCurrentBasketId(), monstertype);
    }

    /**
     * Removes a monster from the customers basket. If the resulting number of monsters reaches 0, the basket item is
     * removed.
     *
     * @param monstertype name of the monstertype to be removed
     */
    @RequestMapping(value = "/basket/{monstertype}",  method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String monstertype){
        basketService.removeItemFromBasket(getCurrentBasketId(), monstertype);
    }

    /**
     * Calculates the sum of (price * number) for all items in the basket.
     */
    @RequestMapping(value = "/basket/sum",  method=RequestMethod.GET)
    @ResponseBody
    public BasketSum sum(){
        double sum = 0;

        for (BasketLineItemJson item : getBasketJson().values()) {
            sum = sum + item.getLineItemPrice();
        }

        return new BasketSum(sum);
    }

}
