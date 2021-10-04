package com.alfa.tishkin.controller;

import com.alfa.tishkin.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/{code}", produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody
    byte[] getCurrencyExchangeRate(@PathVariable("code") String currencyCode) {
        byte[] bytes = null;
        String url = currencyService.getGifByCurrencyCode(currencyCode);
        try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream())) {
            bytes = input.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}

