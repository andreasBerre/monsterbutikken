package no.borber.monsterShop.restApi;

import no.borber.monsterShop.application.BasketApplicationService;
import no.borber.monsterShop.readLayer.basket.BasketLineItemJson;
import no.borber.monsterShop.readLayer.basket.Baskets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@RestController
public class BasketController extends MonsterShopController{

    @Autowired
    BasketApplicationService basketService;

    @Autowired
    Baskets baskets;

    /**
     * Gets the current state of a customers basket
     *
     * @return List of basket line items in the current customers basket.
     */
    @RequestMapping(value = "/basket/",  method=RequestMethod.GET)
    public Collection<BasketLineItemJson> getBasket(){
        return baskets.getBasketLineItems(getCurrentBasketId());
    }

    /**
     * Adds a new monster of a specified type to the customers basket. If there is an existing basket item the number
     * of monsters is incremented, otherwise a new basket item is created.
     *
     * @param monstertype type of monster to be added to the basket
     */
    @RequestMapping(value = "/basket/{monstertype}",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void add(@PathVariable String monstertype){
        if (getCurrentBasketId() == null)
            createBasket();
        basketService.addItemToBasket(getCurrentBasketId(), monstertype);
    }

    /**
     * Removes a monster from the customers basket. If the resulting number of monsters reaches 0, the basket item is
     * removed.
     *
     * @param monstertype type of monster to be removed from the basket
     */
    @RequestMapping(value = "/basket/{monstertype}",  method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String monstertype){
        basketService.removeItemFromBasket(getCurrentBasketId(), monstertype);
    }

    private void createBasket() {
        String basketId = UUID.randomUUID().toString();
        basketService.createBasket(basketId, getCurrentCustomerId());
        setCurrentBasketId(basketId);
    }

}
