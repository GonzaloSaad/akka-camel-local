package org.poc.akka.props.provider;

import akka.actor.Props;
import org.poc.akka.camel.route.RouteSupplier;

import java.util.function.Consumer;

public interface PropsProvider {
    Props get(RouteSupplier routeSupplier, Consumer consumer);
}
