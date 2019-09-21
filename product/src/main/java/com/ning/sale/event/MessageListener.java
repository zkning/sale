package com.ning.sale.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("触发消息接收事件:" + event.getSource());
    }
}
