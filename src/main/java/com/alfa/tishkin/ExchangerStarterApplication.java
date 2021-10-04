package com.alfa.tishkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangerStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangerStarterApplication.class);
    }
}
