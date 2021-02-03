package com.training.micro.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Order {

    @NotNull
    private Long         orderId;
    @NotNull
    @Size(min = 1)
    private List<String> meals;

    private Integer      amount;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final Long orderIdParam) {
        this.orderId = orderIdParam;
    }

    public List<String> getMeals() {
        return this.meals;
    }

    public void setMeals(final List<String> mealsParam) {
        this.meals = mealsParam;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amountParam) {
        amount = amountParam;
    }


}
