package org.poc.akka.props.provider.camel;

import akka.actor.Props;
import org.poc.akka.camel.AkkaCamelConsumer;
import org.poc.akka.camel.route.RouteSupplier;
import org.poc.akka.props.provider.PropsProvider;

import java.util.function.Consumer;

public class AkkaCamelConsumerPropsProvider implements PropsProvider {

    @Override
    public Props get(RouteSupplier routeSupplier, Consumer consumer) {
        return Props.create(AkkaCamelConsumer.class,
                () -> new AkkaCamelConsumer(routeSupplier, consumer));
    }
}
