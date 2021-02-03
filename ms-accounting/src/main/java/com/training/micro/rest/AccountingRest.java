package com.training.micro.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.training.micro.rest.model.PaymentRequest;

@RestController
@RequestMapping("/api/v1/payment")
public class AccountingRest {

    @Value("${server.port}")
    private int port;

    @PostMapping("/charge")
    public String charge(@Validated @RequestBody final PaymentRequest pr) {
        return "OK-" + this.port;
    }

    int counter = 0;

    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "testFallback",
                    commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                                                         value = "1000"))
    public String test() {
        this.counter++;
        if ((this.counter % 3) == 0) {
            throw new IllegalStateException();
        }
        if ((this.counter % 5) == 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
        return "OK";
    }

    public String testFallback() {
        return "OK-ACC-FALLBACK";
    }

}
