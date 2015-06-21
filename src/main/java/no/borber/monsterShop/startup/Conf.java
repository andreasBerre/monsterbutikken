package no.borber.monsterShop.startup;

import akka.actor.ActorSystem;
import no.borber.monsterShop.application.order.OrderApplicationService;
import no.borber.monsterShop.eventStore.EventStore;
import no.borber.monsterShop.projections.BasketListener;
import no.borber.monsterShop.projections.OrderProjection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

@Configuration
public class Conf {
    private final EventStore eventStore;

    public Conf() {
        this.eventStore = new EventStore();
    }

    @Bean
    public OrderApplicationService createOrderApplicationService() {
        return new OrderApplicationService(eventStore);
    }



    @Bean
    public OrderProjection createOrderProjection(){
        return new OrderProjection(eventStore);
    }

    @Bean
    public ActorSystem createActorSystem () throws IOException {
                String url = "http://localhost:2113/projection/$by_category/command/enable";
                Executor executor = Executor.newInstance()
                        .auth(new HttpHost("localhost", 2113), "admin", "changeit")
                        .authPreemptive(new HttpHost("localhost", 2113));

                executor.execute(Request.Post(url));

        return ActorSystem.create();
    }
}

