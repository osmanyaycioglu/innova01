package com.training.micro.rest.error;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ErrorDecoderImpl implements ErrorDecoder {

    @Autowired
    private ErrorAdvice ea;

    @Override
    public RestException decode(final String methodKeyParam,
                                final Response responseParam) {
        if (responseParam.status() != 404) {
            ObjectMapper mapperLoc = new ObjectMapper();
            try {
                ErrorObj readValueLoc = mapperLoc.readValue(responseParam.body()
                                                                         .asInputStream(),
                                                            ErrorObj.class);
                return new RestException(readValueLoc);
            } catch (Exception e) {
                e.printStackTrace();
                return new RestException(new ErrorObj().setSubDomain(this.ea.subDomainName)
                                                       .setBoundedContext(this.ea.boundedContextName)
                                                       .setMicroservice(this.ea.msName)
                                                       .setDesc("Error while decoding errorobj from json : "
                                                                + e.getMessage())
                                                       .setCause(505)
                                                       .init());
            }
        }
        return new RestException(new ErrorObj().setSubDomain(this.ea.subDomainName)
                                               .setBoundedContext(this.ea.boundedContextName)
                                               .setMicroservice(this.ea.msName)
                                               .setDesc("Error while calling other ms")
                                               .setCause(404)
                                               .init());
    }

}
