package org.poc.akka.akka;

import akka.actor.Props;
import org.poc.akka.configuration.SystemConfiguration;
import org.poc.akka.context.ContextConfiguration;

public class AkkaSystemRunner implements Runnable {

    private final SystemConfiguration systemConfiguration;

    public AkkaSystemRunner(SystemConfiguration systemConfiguration) {
        this.systemConfiguration = systemConfiguration;
    }

    @Override
    public void run() {
        Props props = systemConfiguration.getProps();
        ContextConfiguration configuration = systemConfiguration.getContextConfiguration();
        AkkaSystem akkaSystem = new AkkaSystem(props, configuration);
        akkaSystem.run();
    }
}
