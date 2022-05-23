package com.assets.services.Helpers;

import java.math.BigDecimal;

public class PriceByFlight {
    private final BigDecimal firstPrice;
    private final BigDecimal businessPrice;
    private final BigDecimal economyPrice;

    public String getFirstPrice() {
        return firstPrice.toString();
    }

    public String getBusinessPrice() {
        return businessPrice.toString();
    }

    public String getEconomyPrice() {
        return economyPrice.toString();
    }

    public PriceByFlight(BigDecimal firstPrice, BigDecimal businessPrice, BigDecimal economyPrice) {
        this.firstPrice = firstPrice;
        this.businessPrice = businessPrice;
        this.economyPrice = economyPrice;
    }
}
