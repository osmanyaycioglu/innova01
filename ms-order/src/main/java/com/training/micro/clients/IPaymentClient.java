package com.training.micro.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.micro.model.PaymentRequest;
import com.training.micro.rest.error.RestException;


@FeignClient(name = "ACCOUNTING", fallback = PaymentFallback.class)
@RequestMapping("/api/v1/payment")
public interface IPaymentClient {

    @PostMapping("/charge")
    public String charge(@RequestBody final PaymentRequest pr) throws RestException;

}
