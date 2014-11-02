package no.borber.monsterShop.startup;

import no.borber.monsterShop.application.BasketApplicationService;
import no.borber.monsterShop.application.OrderApplicationService;
import no.borber.monsterShop.readLayer.basket.Baskets;
import no.borber.monsterShop.readLayer.order.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {
    public Conf() {
        //TODO: Should probably create the eventstore here to wire it into the services and projections
    }

    @Bean
    public BasketApplicationService createBasketApplicationService() {
        return null;
    }

    @Bean
    public OrderApplicationService createOrderApplicationService() {
        return null;
    }

    @Bean
    public Baskets createBasketProjection() {
        return null;
    }

    @Bean
    public Orders createOrderProjection(){
        return null;
    }
}

