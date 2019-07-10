package org.poc.akka.registry;

import org.apache.camel.impl.SimpleRegistry;

public interface RegistryProvider {
    SimpleRegistry getRegistry();
}
