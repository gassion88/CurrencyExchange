package com.gassion.currencyexchange.entities.jsonResponse;

import java.math.BigDecimal;

public class ExchangeJson implements JsonPresent {
    private CurrencyJson baseCurrency;
    private CurrencyJson targetCurrency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;

    public ExchangeJson(CurrencyJson baseCurrency, CurrencyJson targetCurrency, BigDecimal rate, BigDecimal amount, BigDecimal convertedAmount) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate.setScale(2);
        this.amount = amount.setScale(2);
        this.convertedAmount = convertedAmount.setScale(2);
    }

    public CurrencyJson getBaseCurrency() {
        return baseCurrency;
    }

    public CurrencyJson getTargetCurrency() {
        return targetCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setBaseCurrency(CurrencyJson baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setTargetCurrency(CurrencyJson targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
