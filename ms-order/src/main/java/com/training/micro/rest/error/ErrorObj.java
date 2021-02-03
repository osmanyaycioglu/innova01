package com.training.micro.rest.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorObj {

    private List<ErrorObj> subErrors;
    private String         subDomain;
    private String         boundedContext;
    private String         microservice;
    private String         desc;
    private int            cause;


    public ErrorObj() {
    }


    public List<ErrorObj> getSubErrors() {
        return this.subErrors;
    }

    public ErrorObj setSubErrors(final List<ErrorObj> subErrorsParam) {
        this.subErrors = subErrorsParam;
        return this;
    }

    public ErrorObj addSubError(final ErrorObj err) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(err);
        return this;
    }

    public String getSubDomain() {
        return this.subDomain;
    }

    public ErrorObj setSubDomain(final String subDomainParam) {
        this.subDomain = subDomainParam;
        return this;
    }

    public String getBoundedContext() {
        return this.boundedContext;
    }

    public ErrorObj setBoundedContext(final String boundedContextParam) {
        this.boundedContext = boundedContextParam;
        return this;
    }

    public String getMicroservice() {
        return this.microservice;
    }

    public ErrorObj setMicroservice(final String microserviceParam) {
        this.microservice = microserviceParam;
        return this;
    }

    public String getDesc() {
        return this.desc;
    }

    public ErrorObj setDesc(final String descParam) {
        this.desc = descParam;
        return this;
    }

    public int getCause() {
        return this.cause;
    }

    public ErrorObj setCause(final int causeParam) {
        this.cause = causeParam;
        return this;
    }

    public ErrorObj init() {
        // Init code
        return this;
    }


    public static void main(final String[] args) {
        ErrorObj errorObj = new ErrorObj().setSubDomain("IT")
                                          .setBoundedContext("CRM")
                                          .setDesc("error")
                                          .setMicroservice("xyz")
                                          .setCause(100)
                                          .init();
    }

}
