package com.training.micro.clients;

import com.training.micro.model.PaymentRequest;
import com.training.micro.rest.error.RestException;

public class PaymentFallback implements IPaymentClient {

    @Override
    public String charge(final PaymentRequest prParam) throws RestException {
        return "OK-Fallback";
    }

}
