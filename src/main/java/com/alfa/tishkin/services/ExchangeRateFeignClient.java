package com.alfa.tishkin.services;

import com.alfa.tishkin.models.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-client", url = "${exchange.rates.url}")
public interface ExchangeRateFeignClient {

    @GetMapping("/latest.json?app_id=${exchange.rates.app-id}&symbols={code}")
    ExchangeRateResponse getLatestRate(@PathVariable(value = "code") String code);

    @GetMapping("/historical/{date}.json?app_id=${exchange.rates.app-id}&symbols={code}")
    ExchangeRateResponse getHistoricalRate(@PathVariable(value = "date") String date, @PathVariable(value = "code") String code);
}