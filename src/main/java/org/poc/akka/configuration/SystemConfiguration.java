package org.poc.akka.configuration;

import akka.actor.Props;
import org.poc.akka.context.ContextConfiguration;

public interface SystemConfiguration {
    Props getProps();
    ContextConfiguration getContextConfiguration();
}
