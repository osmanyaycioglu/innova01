package com.training.micro.rest.error;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {

    @Value("${spring.application.name}")
    String msName;
    @Value("${application.subdomain}")
    String subDomainName;
    @Value("${application.bounded.context}")
    String boundedContextName;


    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorObj handle(final RestException exp) {
        return new ErrorObj().setSubDomain(this.subDomainName)
                             .setBoundedContext(this.boundedContextName)
                             .setMicroservice(this.msName)
                             .setDesc("Error from other MS")
                             .setCause(301)
                             .addSubError(exp.getErr())
                             .init();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(final IllegalArgumentException exp) {
        return new ErrorObj().setSubDomain(this.subDomainName)
                             .setBoundedContext(this.boundedContextName)
                             .setMicroservice(this.msName)
                             .setDesc(exp.getMessage())
                             .setCause(202)
                             .init();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(final MethodArgumentNotValidException exp) {
        ErrorObj rootError = new ErrorObj().setSubDomain(this.subDomainName)
                                           .setBoundedContext(this.boundedContextName)
                                           .setMicroservice(this.msName)
                                           .setDesc("Validation Error")
                                           .setCause(202)
                                           .init();
        List<ObjectError> allErrorsLoc = exp.getBindingResult()
                                            .getAllErrors();
        for (ObjectError objectErrorLoc : allErrorsLoc) {
            rootError.addSubError(new ErrorObj().setSubDomain(this.subDomainName)
                                                .setBoundedContext(this.boundedContextName)
                                                .setMicroservice(this.msName)
                                                .setDesc(objectErrorLoc.toString())
                                                .setCause(333)
                                                .init());
        }
        return rootError;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handle(final Exception exp) {
        ErrorObj err = new ErrorObj().setSubDomain(this.subDomainName)
                                     .setBoundedContext(this.boundedContextName)
                                     .setMicroservice(this.msName)
                                     .setDesc(exp.getMessage())
                                     .setCause(780)
                                     .init();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .header("err",
                                     "merr")
                             .body(err);
    }

}
