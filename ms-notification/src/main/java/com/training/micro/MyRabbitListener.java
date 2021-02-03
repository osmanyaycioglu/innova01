package com.training.micro;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "notf_req", durable = "true", autoDelete = "false"),
                                             exchange = @Exchange(name = "notf_exchange",
                                                                  durable = "true",
                                                                  autoDelete = "false",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "notification_request"))
    public void handle(final String str) {
        System.out.println("Handling  : " + str);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "notf_req_2",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notf_exchange_topic",
                                                                  durable = "true",
                                                                  autoDelete = "false",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "notify.email.#"))
    @SendTo("notf_response_exchange/notification_response")
    public String handle2(final Notification str) {
        System.out.println("Handling  notify.email.# : " + str);
        return "Handled by notify.email.# : " + str;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "notf_req_3",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notf_exchange_topic",
                                                                  durable = "true",
                                                                  autoDelete = "false",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "notify.#"))
    @SendTo("notf_response_exchange/notification_response")
    public String handle3(final Notification str) {
        System.out.println("Handling  notify.# : " + str);
        return "Handled by notify.# : " + str;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "notf_req_4",
                                                            durable = "true",
                                                            autoDelete = "false"),
                                             exchange = @Exchange(name = "notf_exchange_topic",
                                                                  durable = "true",
                                                                  autoDelete = "false",
                                                                  type = ExchangeTypes.TOPIC),
                                             key = "notify.*.abc"))
    @SendTo("notf_response_exchange/notification_response")
    public String handle4(final Notification str) {
        System.out.println("Handling  notify.*.abc : " + str);
        return "Handled by notify.*.abc : " + str;
    }

}
