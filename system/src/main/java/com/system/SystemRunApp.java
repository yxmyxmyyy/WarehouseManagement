package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.api.config"),
        @ComponentScan("com.common.advice")
})
public class SystemRunApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemRunApp.class, args);
    }
}
