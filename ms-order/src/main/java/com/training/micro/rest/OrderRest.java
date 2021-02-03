package com.training.micro.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.micro.model.Order;
import com.training.micro.rest.error.RestException;
import com.training.micro.services.OrderService;

@RestController
@RequestMapping("/api/v1/order")
@RefreshScope
public class OrderRest {

    @Value("${a.c.d}")
    private String       acd;

    @Autowired
    private OrderService os;

    @GetMapping("/test")
    public String test() {
        return this.acd;
    }

    @PostMapping("/place")
    public String placeOrder(@Validated @RequestBody final Order order) {
        return this.os.placeOrder(order);
    }

    @PostMapping("/place2")
    public String placeOrder2(@Validated @RequestBody final Order order) throws RestException {
        return this.os.placeOrder2(order);
    }

}
