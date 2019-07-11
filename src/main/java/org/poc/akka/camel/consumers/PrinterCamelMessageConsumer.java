package org.poc.akka.camel.consumers;

import akka.camel.CamelMessage;

import java.util.function.Consumer;

public class PrinterCamelMessageConsumer implements Consumer {

    @Override
    public void accept(Object object) {
        if(object instanceof CamelMessage){
            CamelMessage camelMessage = (CamelMessage) object;
            System.out.println(camelMessage);
        }
    }
}
