package com.alfa.tishkin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gifs-client", url = "${giphy.url}")
public interface GifsFeignClient {

    @GetMapping("?api_key=${giphy.app-id}&tag={category}")
    String getJsonFromGiphy(@PathVariable(value = "category") String gifsCategory);
}
