package com.ning.sale.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MessageEventPublisher {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void send(String message) {
        MessageEvent messageEvent = new MessageEvent(message);
        applicationEventPublisher.publishEvent(messageEvent);
    }
}
