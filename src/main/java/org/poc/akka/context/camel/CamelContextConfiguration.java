package org.poc.akka.context.camel;

import akka.actor.ActorSystem;
import akka.camel.Camel;
import akka.camel.CamelExtension;
import org.poc.akka.camel.registry.RegistryProvider;
import org.poc.akka.context.ContextConfiguration;

import java.util.Optional;

public class CamelContextConfiguration implements ContextConfiguration {
    private final RegistryProvider registryProvider;

    public CamelContextConfiguration(RegistryProvider registryProvider) {
        this.registryProvider = registryProvider;
    }

    @Override
    public void setRegistry(ActorSystem actorSystem) {
        Optional.ofNullable(CamelExtension.get(actorSystem))
                .map(Camel::context)
                .ifPresent(context -> context.setRegistry(registryProvider.getRegistry()));
    }
}
