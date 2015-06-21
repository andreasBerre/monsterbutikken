package no.borber.monsterShop.startup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import no.borber.monsterShop.projections.BasketListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Actors {
    @Autowired
    ActorSystem system;

    @Bean(name="basketProjection")
    public ActorRef createBasketProjection() {
        return system.actorOf(BasketListener.props());
    }
}
