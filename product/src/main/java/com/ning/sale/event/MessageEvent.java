package com.ning.sale.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param message the object on which the event initially occurred (never {@code null})
     */
    public MessageEvent(String message) {
        super(message);
    }
}
