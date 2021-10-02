package com.alfa.tishkin.services;

import com.alfa.tishkin.models.ExchangeRateResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    ExchangeRateFeignClient exchangeRateFeignClient;

    @Autowired
    GifsFeignClient gifsFeignClient;

    public String getGifByCurrencyCode(String currencyCode) {
        double latestRate = getLatestCurrencyExchangeRate(currencyCode);
        double historicalRate = getHistoricalCurrencyExchangeRate(currencyCode);
        return latestRate - historicalRate > 0 ? getGifUrl("rich") : getGifUrl("broke");
    }

    private double getLatestCurrencyExchangeRate(String currencyCode) {
        ExchangeRateResponse response = exchangeRateFeignClient.getLatestRate(currencyCode);
        return response.getRates().get(currencyCode);
    }

    private double getHistoricalCurrencyExchangeRate(String currencyCode) {
        String date = LocalDate.now().minusDays(1).toString();
        ExchangeRateResponse response = exchangeRateFeignClient.getHistoricalRate(date, currencyCode);
        return response.getRates().get(currencyCode);
    }

    private String getGifUrl(String tag) {
        String json = gifsFeignClient.getJsonFromGiphy(tag);
        JsonObject obj = new Gson().fromJson(json, JsonObject.class);
        return obj
                .getAsJsonObject("data")
                .getAsJsonObject("images")
                .getAsJsonObject("downsized_large")
                .get("url").getAsString()
                .replaceFirst("media\\d?", "i");
    }
}
