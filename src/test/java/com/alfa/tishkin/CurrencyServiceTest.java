package com.alfa.tishkin;

import com.alfa.tishkin.models.ExchangeRateResponse;
import com.alfa.tishkin.services.CurrencyService;
import com.alfa.tishkin.services.ExchangeRateFeignClient;
import com.alfa.tishkin.services.GifsFeignClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ExchangerStarterApplication.class)
public class CurrencyServiceTest {

    @MockBean
    private ExchangeRateFeignClient mockExchangeClient;

    @MockBean
    private GifsFeignClient mockGifsClient;

    @Autowired
    private CurrencyService currencyService;

    private static final String currencyCode = "RUB";
    private static final String tagForGif = "broke";
    private static ExchangeRateResponse exchangeRateResponse;
    private static String jsonWithGif;

    @BeforeAll
    static void init() {
        double exchangeRate = 72.76;
        Map<String, Double> rates = new HashMap<>();
        rates.put(currencyCode, exchangeRate);
        exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setRates(rates);
        jsonWithGif = "{\"data\":{\"type\":\"gif\",\"id\":\"sC0P86Fox5RkI\",\"slug\":\"rich-sC0P86Fox5RkI\",\"images\":{\"downsized_large\":{\"height\": \"148\",\"size\": \"1096570\",\"url\":\"https://media1.giphy.com/media\"}}}}";
    }

    @Test
    public void get_gifUrl_thenReturnGifUrlFromGiphyResource() {
        when(mockExchangeClient.getLatestRate(currencyCode)).thenReturn(exchangeRateResponse);
        when(mockExchangeClient.getHistoricalRate(any(String.class), eq(currencyCode))).thenReturn(exchangeRateResponse);
        when(mockGifsClient.getJsonFromGiphy(tagForGif)).thenReturn(jsonWithGif);

        String expectedGifUrl = "https://i.giphy.com/media";
        String actualGifUrl = currencyService.getGifByCurrencyCode(currencyCode);
        Assertions.assertEquals(expectedGifUrl, actualGifUrl);
        verify(mockExchangeClient, times(1)).getLatestRate(currencyCode);
        verify(mockExchangeClient, times(1)).getHistoricalRate(any(String.class), eq(currencyCode));
        verify(mockGifsClient, times(1)).getJsonFromGiphy(tagForGif);
    }
}
