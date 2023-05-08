package com.gassion.currencyexchange.entities.factories;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.ExchangeRate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

public class ExchangeRateFactory {
    public static ExchangeRate getInUrl(Map<String, String[]> parameterMap) {
        String baseCurrencyCode = parameterMap.get("baseCurrencyCode")[0];
        String targetCurrencyCode = parameterMap.get("targetCurrencyCode")[0];
        BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(parameterMap.get("rate")[0]));

        Currency baseCurrency = null;
        Currency targetCurrency = null;
        try {
            baseCurrency = new CurrencyDAO().get(baseCurrencyCode);
            targetCurrency = new CurrencyDAO().get((targetCurrencyCode));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ExchangeRate(0, baseCurrency.getId(), targetCurrency.getId(), rate);
    }
}
