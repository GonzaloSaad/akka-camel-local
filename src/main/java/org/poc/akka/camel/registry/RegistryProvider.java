package org.poc.akka.camel.registry;

import org.apache.camel.impl.SimpleRegistry;

public interface RegistryProvider {
    SimpleRegistry getRegistry();
}
