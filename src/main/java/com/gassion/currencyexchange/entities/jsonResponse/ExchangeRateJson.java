package com.gassion.currencyexchange.entities.jsonResponse;

import java.math.BigDecimal;

public class ExchangeRateJson {
    private final int id;
    private final int baseCurrencyId;
    private final int targetCurrencyId;
    private BigDecimal rate;

    public ExchangeRateJson(int id, int baseCurrencyId, int targetCurrencyId, BigDecimal rate) {
        this.id = id;
        this.baseCurrencyId = baseCurrencyId;
        this.targetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public int getBaseCurrencyId() {
        return baseCurrencyId;
    }

    public int getTargetCurrencyId() {
        return targetCurrencyId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
