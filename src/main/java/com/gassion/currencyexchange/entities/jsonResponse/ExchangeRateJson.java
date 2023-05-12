package com.gassion.currencyexchange.entities.jsonResponse;

import java.math.BigDecimal;

public class ExchangeRateJson implements JsonPresent {
    private final int id;
    private CurrencyJson baseCurrency;
    private CurrencyJson targetCurrency;
    private BigDecimal rate;

    public ExchangeRateJson(int id, CurrencyJson baseCurrency, CurrencyJson targetCurrency, BigDecimal rate) {
        this.id = id;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate.setScale(2);
    }

    public int getId() {
        return id;
    }

    public CurrencyJson getBaseCurrency() {
        return baseCurrency;
    }

    public CurrencyJson getTargetCurrency() {
        return targetCurrency;
    }

    public void setBaseCurrency(CurrencyJson baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(CurrencyJson targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
