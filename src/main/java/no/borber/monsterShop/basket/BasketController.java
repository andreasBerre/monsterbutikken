package no.borber.monsterShop.basket;

import no.borber.monsterShop.MonsterShopController;
import no.borber.monsterShop.monsterTypes.MonsterTypesRepo;
import no.borber.monsterShop.orders.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class BasketController extends MonsterShopController{

    @Autowired
    BasketApplicationService basketService;

    @Autowired
    BasketProjection basketProjection;

    /**
     * Gets the current state of a customers basket
     *
     * @return List of basket line items for the current customer.
     */
    @RequestMapping(value = "/basket/",  method=RequestMethod.GET)
    public List<BasketLineItemJson> getBasket(){
        return getBasketJson();
    }

    private List<BasketLineItemJson> getBasketJson() {
        List<BasketLineItemJson> basketLineItemsJson = new ArrayList<>();

        for (BasketLineItem item : basketProjection.getBasket(getCurrentBasketId()).getBasketLineItems()) {
            double lineItemPrice = MonsterTypesRepo.getMonsterType(item.getMonsterType()).getPrice() * item.getCount();
            basketLineItemsJson.add(new BasketLineItemJson(item.getMonsterType(), item.getCount(), lineItemPrice));
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
     * Checks out the customers current basket and creates an order based on the basket contents.
     */
    @RequestMapping(value = "/basket/checkout",  method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void checkout(){
        basketService.checkoutBasket(getCurrentBasketId(), getCurrentCustomerId(), new OrderId(UUID.randomUUID().toString()));
    }

    /**
     * Calculates the sum of (price * number) for all items in the basket.
     */
    @RequestMapping(value = "/basket/sum",  method=RequestMethod.GET)
    public BasketSum sum(){
        double sum = 0;

        for (BasketLineItemJson item : getBasketJson())
            sum = sum + item.getLineItemPrice();

        return new BasketSum(sum);
    }

}
