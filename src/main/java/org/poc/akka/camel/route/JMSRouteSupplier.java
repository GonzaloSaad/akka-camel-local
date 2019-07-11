package org.poc.akka.camel.route;

public class JMSRouteSupplier implements RouteSupplier {

    private final String queueName;
    private final String referenceName;

    private static final String URI_TEMPLATE = "jms:queue:%s?connectionFactory=#%s" +
            "&asyncConsumer=true&maxConcurrentConsumers=6&cacheLevelName=CACHE_NONE";

    public JMSRouteSupplier(String queueName, String referenceName) {
        this.queueName = queueName;
        this.referenceName = referenceName;
    }

    @Override
    public String get() {
        return String.format(URI_TEMPLATE, queueName, referenceName);
    }
}
