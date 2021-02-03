package com.training.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class NodeConfigApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NodeConfigApplication.class,
                              args);
    }

}
