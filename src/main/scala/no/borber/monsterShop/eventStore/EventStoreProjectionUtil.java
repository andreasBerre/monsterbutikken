package no.borber.monsterShop.eventStore;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

/**
 * Created by idar on 11/9/14.
 */
public class EventStoreProjectionUtil {

    public static void startByCategoryProjection() throws IOException {
        String url = "http://localhost:2113/projection/$by_category/command/enable";
        Executor executor = Executor.newInstance()
                .auth(new HttpHost("localhost", 2113), "admin", "changeit")
                .authPreemptive(new HttpHost("localhost", 2113));

        executor.execute(Request.Post(url));


    }
}
