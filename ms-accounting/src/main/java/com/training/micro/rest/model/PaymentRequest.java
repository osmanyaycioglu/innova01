package com.training.micro.rest.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PaymentRequest {

    @NotNull
    @Size(min = 5, max = 20)
    private String  customerId;

    @NotNull
    @Max(1000)
    @Positive
    private Integer amount;


    public String getCustomerId() {
        return this.customerId;
    }


    public void setCustomerId(final String customerIdParam) {
        this.customerId = customerIdParam;
    }


    public Integer getAmount() {
        return this.amount;
    }


    public void setAmount(final Integer amountParam) {
        this.amount = amountParam;
    }


}
