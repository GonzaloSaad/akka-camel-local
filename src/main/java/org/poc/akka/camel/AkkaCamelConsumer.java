package org.poc.akka.camel;

import akka.camel.javaapi.UntypedConsumerActor;
import org.poc.akka.camel.route.RouteSupplier;

import java.util.function.Consumer;

public class AkkaCamelConsumer extends UntypedConsumerActor {

    private final RouteSupplier routeSupplier;
    private final Consumer camelMessageConsumer;

    public AkkaCamelConsumer(RouteSupplier routeSupplier, Consumer camelMessageConsumer) {
        this.routeSupplier = routeSupplier;
        this.camelMessageConsumer = camelMessageConsumer;
    }

    @Override
    public String getEndpointUri() {
        return routeSupplier.get();
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        camelMessageConsumer.accept(message);
    }

}
