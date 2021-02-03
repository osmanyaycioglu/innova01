package com.training.micro.services;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.training.micro.clients.IPaymentClient;
import com.training.micro.model.Notification;
import com.training.micro.model.Order;
import com.training.micro.model.PaymentRequest;
import com.training.micro.rest.error.RestException;

@Service
public class OrderService {

    @Autowired
    private RestTemplate   rt;

    @Autowired
    private IPaymentClient pc;

    @Autowired
    private EurekaClient   ec;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String placeOrder(final Order order) {
        PaymentRequest pay = new PaymentRequest();
        pay.setCustomerId("JHKJH-JKHKH");
        pay.setAmount(100);
        String postForObjectLoc = this.rt.postForObject("http://ACCOUNTING/api/v1/payment/charge",
                                                        pay,
                                                        String.class);
        return postForObjectLoc;
    }

    public String placeOrder2(final Order order) throws RestException {
        PaymentRequest pay = new PaymentRequest();
        pay.setCustomerId("JHKJH-JKHKH");
        pay.setAmount(order.getAmount());
        String chargeLoc = this.pc.charge(pay);
        Notification notificationLoc = new Notification();
        notificationLoc.setDestination("23847239847");
        notificationLoc.setMessage("Sayın Osman , Siparişiniz en kısa zamanada gönderilecek.");
        this.rabbitTemplate.convertAndSend("notf_exchange_topic",
                                           "notify.email.xyz",
                                           notificationLoc);
        return chargeLoc;
    }


    public String placeOrder3(final Order order) {
        Application applicationLoc = this.ec.getApplication("ACCOUNTING");
        List<InstanceInfo> instancesLoc = applicationLoc.getInstances();
        for (InstanceInfo instanceInfoLoc : instancesLoc) {
            System.out.println(instanceInfoLoc);
        }
        InstanceInfo instanceLoc = this.ec.getNextServerFromEureka("ACCOUNTING",
                                                                   false);
        PaymentRequest pay = new PaymentRequest();
        pay.setCustomerId("JHKJH-JKHKH");
        pay.setAmount(100);
        RestTemplate rtl = new RestTemplate();
        String postForObjectLoc = rtl.postForObject("http://"
                                                    + instanceLoc.getIPAddr()
                                                    + ":"
                                                    + instanceLoc.getPort()
                                                    + "/api/v1/payment/charge",
                                                    pay,
                                                    String.class);
        return postForObjectLoc;
    }

}
