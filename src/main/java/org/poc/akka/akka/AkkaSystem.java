package org.poc.akka.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import org.poc.akka.context.ContextConfiguration;

public class AkkaSystem implements Runnable {

    private final Props props;
    private final ContextConfiguration contextConfiguration;

    public AkkaSystem(Props props, ContextConfiguration contextConfiguration) {
        this.props = props;
        this.contextConfiguration = contextConfiguration;
    }

    @Override
    public void run() {
        try {
            final ActorSystem system = ActorSystem.create();
            contextConfiguration.setRegistry(system);
            system.actorOf(props);
            Thread.sleep(10000);
            system.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
