package com.gassion.currencyexchange.entities;

import com.gassion.currencyexchange.DAO.JsonPresent;
import com.gassion.currencyexchange.entities.jsonResponse.ExchangeRateJson;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class ExchangeRate implements JsonPresent<ExchangeRateJson> {
    private final int id;
    private final int baseCurrencyId;
    private final int targetCurrencyId;
    private BigDecimal rate;

    public ExchangeRate(int id, int baseCurrencyId, int targetCurrencyId, BigDecimal rate) {
        this.id = id;
        this.baseCurrencyId = baseCurrencyId;
        this.targetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }

    public static ExchangeRate factory(Map<String, String[]> parameterMap) {
        int baseCurrencyId = Integer.parseInt(parameterMap.get("baseCurrencyId")[0]);
        int targetCurrencyId = Integer.parseInt(parameterMap.get("targetCurrencyId")[0]);
        BigDecimal rate = BigDecimal.valueOf(Long.parseLong(parameterMap.get("rate")[0]));

        return new ExchangeRate(0, baseCurrencyId, targetCurrencyId, rate);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRate that = (ExchangeRate) o;

        if (id != that.id) return false;
        if (baseCurrencyId != that.baseCurrencyId) return false;
        if (targetCurrencyId != that.targetCurrencyId) return false;
        return Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + baseCurrencyId;
        result = 31 * result + targetCurrencyId;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", baseCurrencyId=" + baseCurrencyId +
                ", targetCurrencyId=" + targetCurrencyId +
                ", rate=" + rate +
                '}';
    }

    @Override
    public ExchangeRateJson getJsonPesent() {
        return new ExchangeRateJson(getId(), getBaseCurrencyId(), getTargetCurrencyId(), getRate());
    }
}
