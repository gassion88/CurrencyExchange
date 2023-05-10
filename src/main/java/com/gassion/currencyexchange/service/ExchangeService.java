package com.gassion.currencyexchange.service;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.DAO.ExchangeRateDAO;
import com.gassion.currencyexchange.entities.jsonResponse.CurrencyJson;
import com.gassion.currencyexchange.entities.jsonResponse.ExchangeJson;
import com.gassion.currencyexchange.utils.OutResponse;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ExchangeService {
    private static final Gson GSON = new Gson();

    public static String exchange(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) throws Exception {
        BigDecimal rate = findExchangeRate(baseCurrencyCode, targetCurrencyCode);
        BigDecimal convertedAmount = rate.multiply(amount);

        CurrencyJson baseCurrencyJson = new CurrencyDAO().get(baseCurrencyCode).getJsonPesent();
        CurrencyJson targetCurrencyJson = new CurrencyDAO().get(targetCurrencyCode).getJsonPesent();
        ExchangeJson exchangeJson = new ExchangeJson(baseCurrencyJson, targetCurrencyJson, rate, amount, convertedAmount);

        return GSON.toJson(exchangeJson);
    }

    private static BigDecimal findExchangeRate(String baseCurrencyCode, String targetCurrencyCode) throws SQLException {
        String directCurrencyPair = baseCurrencyCode + targetCurrencyCode;
        String reverseCurrencyPair =targetCurrencyCode + baseCurrencyCode;

        if (new ExchangeRateDAO().get(directCurrencyPair) != null) {
            return new ExchangeRateDAO().get(directCurrencyPair).getRate();
        }

        if (new ExchangeRateDAO().get(reverseCurrencyPair) != null) {
            return BigDecimal.valueOf(1).divide(new ExchangeRateDAO().get(reverseCurrencyPair).getRate());
        }

        if (new ExchangeRateDAO().get(baseCurrencyCode + "USD") != null ||
                new ExchangeRateDAO().get("USD" + baseCurrencyCode) != null) {

            if (new ExchangeRateDAO().get(targetCurrencyCode + "USD") != null ||
                    new ExchangeRateDAO().get("USD" + targetCurrencyCode) != null) {

                return getRate(baseCurrencyCode, targetCurrencyCode);
            }
        }

        throw new SQLException("Not found", "Error", HttpServletResponse.SC_NOT_FOUND);
    }

    private static BigDecimal getRate(String baseCurrencyCode, String targetCurrencyCode) throws SQLException {
        BigDecimal rate = null;
        if (new ExchangeRateDAO().get(baseCurrencyCode + "USD") != null) {
            rate = new ExchangeRateDAO().get(baseCurrencyCode + "USD").getRate();
        } else {
            rate = BigDecimal.valueOf(1).divide(new ExchangeRateDAO().get("USD" + baseCurrencyCode).getRate());
        }

        if (new ExchangeRateDAO().get("USD" + targetCurrencyCode) != null) {
            rate = rate.multiply(new ExchangeRateDAO().get("USD" + targetCurrencyCode).getRate()) ;
        } else {
            rate = rate.multiply( (BigDecimal.valueOf(1).divide(new ExchangeRateDAO().get(targetCurrencyCode + "USD").getRate())) );
        }

        return rate;
    }
}
