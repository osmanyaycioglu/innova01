package com.training.micro.model;

public class PaymentRequest {

    private String  customerId;
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
