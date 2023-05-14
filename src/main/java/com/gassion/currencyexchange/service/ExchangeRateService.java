package com.gassion.currencyexchange.service;

import com.gassion.currencyexchange.DAO.ExchangeRateDAO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ExchangeRateService {
    private static final ExchangeRateDAO EXCHANGE_RATE_DAO = new ExchangeRateDAO();
    public static List<ExchangeRate> getAllExchangeRate() throws SQLException {
        List<ExchangeRate> exchangeRates = EXCHANGE_RATE_DAO.getAll();

        return exchangeRates;
    }

    public static ExchangeRate addExchangeRate(ExchangeRate exchangeRate, String exchangeRateCode) throws SQLException {
        try {
            EXCHANGE_RATE_DAO.add(exchangeRate);
            exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

            return exchangeRate;
        } catch (SQLException e) {
            throw new SQLException("Currency pair with this code already exists", "Error", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public static ExchangeRate getExchangeRateByCodeRequest(String exchangeRateCode) throws SQLException {
        ExchangeRate exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

        if (exchangeRate == null) {
            throw new SQLException("Currency pair with this code already exists", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        return exchangeRate;
    }

    public static ExchangeRate patchExchangeRateByCodeRequest(String exchangeRateCode, String rate) throws SQLException {
        ExchangeRate exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

        if (exchangeRate == null) {
            throw new SQLException("Currency pair with this code already exists", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        BigDecimal newRate = BigDecimal.valueOf(Double.parseDouble(rate));
        exchangeRate.setRate(newRate);
        EXCHANGE_RATE_DAO.set(exchangeRate);

        return exchangeRate;
    }
}
