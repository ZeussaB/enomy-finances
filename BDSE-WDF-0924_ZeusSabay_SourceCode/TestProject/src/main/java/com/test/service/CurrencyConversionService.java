package com.test.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    // Correct argument order: (double amount, String fromCurrency, String toCurrency)
    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        // In a real-world scenario, fetch exchange rates dynamically
        double conversionRate = 1.1;  // Example fixed rate

        return amount * conversionRate;
    }
}