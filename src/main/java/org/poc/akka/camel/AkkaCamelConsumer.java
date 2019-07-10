package org.poc.akka.camel;

import akka.actor.Props;
import akka.camel.javaapi.UntypedConsumerActor;
import org.poc.akka.supplier.JMSRouteSupplier;

import java.util.function.Supplier;

public class AkkaCamelConsumer extends UntypedConsumerActor {

    private final Supplier<String> routeSupplier;

    public AkkaCamelConsumer(Supplier<String> routeSupplier) {
        this.routeSupplier = routeSupplier;
    }


    @Override
    public String getEndpointUri() {
        return routeSupplier.get();
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(message);
    }

    public static Props props() {
        return Props.create(AkkaCamelConsumer.class, () -> new AkkaCamelConsumer(new JMSRouteSupplier("camelQueue")));
    }
}
