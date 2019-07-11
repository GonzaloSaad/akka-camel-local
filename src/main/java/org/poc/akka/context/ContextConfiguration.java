package org.poc.akka.context;

import akka.actor.ActorSystem;

public interface ContextConfiguration {
    void setRegistry(ActorSystem actorSystem);
}
