package com.training.micro.config;

import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.NoOpPing;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

public class OrderRibbonConfig {

    @Bean
    public IRule myRule() {
        return new WeightedResponseTimeRule();
    }

    @Bean
    public IPing myPing() {
        return new NoOpPing();
    }

}
