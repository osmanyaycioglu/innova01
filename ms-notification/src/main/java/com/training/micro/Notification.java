package com.training.micro;


public class Notification {

    private String destination;
    private String message;

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(final String destinationParam) {
        this.destination = destinationParam;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String messageParam) {
        this.message = messageParam;
    }

    @Override
    public String toString() {
        return "Notification [destination=" + this.destination + ", message=" + this.message + "]";
    }


}
