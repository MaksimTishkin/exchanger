package com.alfa.tishkin;

import com.alfa.tishkin.services.CurrencyService;
import com.alfa.tishkin.services.CurrencyServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ExchangerStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangerStarterApplication.class);
    }

    @Bean
    public CurrencyService currencyService() {
        return new CurrencyServiceImpl();
    }
}
