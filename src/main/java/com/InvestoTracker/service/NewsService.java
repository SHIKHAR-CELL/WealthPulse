package com.InvestoTracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@Service
public class NewsService {

    @Value("${marketaux.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.marketaux.com/v1/news/all";

    public List<Map<String, Object>> fetchIndianMarketNews() {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("api_token", apiKey)
                .queryParam("countries", "in")
                .queryParam("language", "en")
                .queryParam("limit", 5)
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<Map<String, Object>>) response.get("data");
    }
}
