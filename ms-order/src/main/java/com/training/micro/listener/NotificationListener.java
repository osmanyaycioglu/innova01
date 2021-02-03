package com.training.micro.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "notf_res", durable = "true", autoDelete = "false"),
                                             exchange = @Exchange(name = "notf_response_exchange",
                                                                  durable = "true",
                                                                  autoDelete = "false",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "notification_response"))
    public void handle(final String str) {
        System.out.println("Notification response  : " + str);
    }


}
