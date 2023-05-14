package com.gassion.currencyexchange.service;

import com.gassion.currencyexchange.DAO.ExchangeRateDAO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.entities.DTO.ExchangeRateDTO;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ExchangeRateService {
    private static final Gson GSON = new Gson();
    private static final ExchangeRateDAO EXCHANGE_RATE_DAO = new ExchangeRateDAO();
    public static String getAllExchangeRate() throws SQLException {
        List<ExchangeRate> exchangeRates = EXCHANGE_RATE_DAO.getAll();

        List<ExchangeRateDTO> exchangeRatesJson = ValidateUtils.getJsonFormat(exchangeRates);

        return GSON.toJson(exchangeRatesJson);
    }

    public static String addExchangeRate(ExchangeRate exchangeRate, String exchangeRateCode) throws SQLException {
        EXCHANGE_RATE_DAO.add(exchangeRate);
        exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

        if (exchangeRate == null) {
            throw new SQLException("Currency pair with this code already exists", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        ExchangeRateDTO exchangeRateDTO = exchangeRate.getJsonPesent();

        return GSON.toJson(exchangeRateDTO);
    }

    public static String getExchangeRateByCodeRequest(String exchangeRateCode) throws SQLException {
        ExchangeRate exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

        if (exchangeRate == null) {
            throw new SQLException("Currency pair with this code already exists", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        ExchangeRateDTO exchangeRateDTO = exchangeRate.getJsonPesent();

        return GSON.toJson(exchangeRateDTO);
    }

    public static String patchExchangeRateByCodeRequest(String exchangeRateCode, String rate) throws SQLException {
        ExchangeRate exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

        if (exchangeRate == null) {
            throw new SQLException("Currency pair with this code already exists", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        BigDecimal newRate = BigDecimal.valueOf(Double.parseDouble(rate));
        exchangeRate.setRate(newRate);
        EXCHANGE_RATE_DAO.set(exchangeRate);

        ExchangeRateDTO exchangeRateDTO = exchangeRate.getJsonPesent();

        return GSON.toJson(exchangeRateDTO);
    }
}
