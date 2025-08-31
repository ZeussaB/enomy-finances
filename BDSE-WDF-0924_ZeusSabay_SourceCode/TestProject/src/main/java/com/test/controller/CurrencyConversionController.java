package com.test.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/currency")
public class CurrencyConversionController {

    private final String EXCHANGE_RATE_API_URL = "https://api.exchangerate-api.com/v4/latest/";

    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        String fromCurrency = request.getFromCurrency();
        String toCurrency = request.getToCurrency();
        double amount = request.getAmount();

        // Fetch exchange rate data from external API (for example, exchangerate-api.com)
        RestTemplate restTemplate = new RestTemplate();
        String url = EXCHANGE_RATE_API_URL + fromCurrency;

        try {
            // Fetch the rates
            CurrencyResponse currencyResponse = restTemplate.getForObject(url, CurrencyResponse.class);

            if (currencyResponse != null && currencyResponse.getRates().containsKey(toCurrency)) {
                double rate = currencyResponse.getRates().get(toCurrency);
                double convertedAmount = amount * rate;
                return ResponseEntity.ok(new ConversionResult(convertedAmount));
            } else {
                return ResponseEntity.badRequest().body("Invalid currency pair.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching exchange rates.");
        }
    }

    // DTO classes for request/response
    public static class CurrencyConversionRequest {
        private String fromCurrency;
        private String toCurrency;
        private double amount;

        // Getters and setters
    }

    public static class CurrencyResponse {
        private String base;
        private Map<String, Double> rates;

        // Getters and setters
    }

    public static class ConversionResult {
        private double convertedAmount;

        public ConversionResult(double convertedAmount) {
            this.convertedAmount = convertedAmount;
        }

        public double getConvertedAmount() {
            return convertedAmount;
        }
    }
}