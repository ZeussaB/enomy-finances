package com.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Currency {
    @Id
    private String code;
    private String name;
    private double conversionRate;

    // Default constructor (Required by JPA)
    public Currency() {}

    // Parameterized constructor (Optional)
    public Currency(String code, String name, double conversionRate) {
        this.code = code;
        this.name = name;
        this.conversionRate = conversionRate;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}