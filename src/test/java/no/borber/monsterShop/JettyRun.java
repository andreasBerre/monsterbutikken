package no.borber.monsterShop;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

public class JettyRun {

    public static void main(String... args) throws Exception {

        Server server = new Server(9090);
        WebAppContext context = new WebAppContext("src/main/webapp","/");

        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(), new WebXmlConfiguration(),
                new WebInfConfiguration(), new TagLibConfiguration(),
                new PlusConfiguration(), new MetaInfConfiguration(),
                new FragmentConfiguration(), new EnvConfiguration()});

        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        server.start();
        server.join();

        server.setStopAtShutdown(true);
    }
}
