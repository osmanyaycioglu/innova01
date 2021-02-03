package com.training.micro.rest.error;


public class RestException extends Exception {

    private static final long serialVersionUID = 6807295926959712761L;
    private ErrorObj          err;


    public RestException() {
    }


    public RestException(final ErrorObj errParam) {
        super();
        this.err = errParam;
    }


    public ErrorObj getErr() {
        return this.err;
    }


    public void setErr(final ErrorObj errParam) {
        this.err = errParam;
    }


}
