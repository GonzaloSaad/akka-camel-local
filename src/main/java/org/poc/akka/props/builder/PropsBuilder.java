package org.poc.akka.props.builder;

import akka.actor.Props;
import org.poc.akka.camel.route.RouteSupplier;
import org.poc.akka.props.provider.PropsProvider;

import java.util.function.Consumer;

public class PropsBuilder {
    private PropsProvider propsProvider;
    private RouteSupplier routeSupplier;
    private Consumer consumer;

    public PropsBuilder withConsumer(Consumer consumer) {
        this.consumer = consumer;
        return this;
    }

    public PropsBuilder withRouteSupplier(RouteSupplier routeSupplier) {
        this.routeSupplier = routeSupplier;
        return this;
    }

    public PropsBuilder withPropsProvider(PropsProvider propsProvider) {
        this.propsProvider = propsProvider;
        return this;
    }

    public Props build() {
        return propsProvider.get(routeSupplier, consumer);
    }
}
