package com.gassion.currencyexchange.service;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

public class CurrencyService {
    private static final Gson GSON = new Gson();
    private static final CurrencyDAO CURRENCY_DAO = new CurrencyDAO();
    public static String getAllCurrenciesRequest() throws Exception {
            List<Currency> currencies = CURRENCY_DAO.getAll();
            List<CurrencyDTO> currenciesJson = ValidateUtils.getJsonFormat(currencies);

            return GSON.toJson(currenciesJson);
    }

    public static String addCurrency(Currency currency) throws SQLException {
        try {
            CURRENCY_DAO.add(currency);
            currency = CURRENCY_DAO.get(currency.getCode());

            return GSON.toJson(currency.getJsonPesent());
        } catch (SQLException e) {
            throw new SQLException("Currency with this code already exists", "Error", HttpServletResponse.SC_CONFLICT);
        }
    }

    public static String getCurrencyByCodeRequest(String currencyCode) throws Exception {
        Currency currency = CURRENCY_DAO.get(currencyCode);

        if (currency == null) {
            throw new SQLException("Currency not found", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        CurrencyDTO currencyDTO = currency.getJsonPesent();

        return GSON.toJson(currencyDTO);
    }

    public static String deleteCurrencyByCodeRequest(String currencyCode) throws Exception {
        Currency currency = CURRENCY_DAO.get(currencyCode);

        if (currency == null) {
            throw new SQLException("Currency not found", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        new CurrencyDAO().delete(currency.getId());

        return GSON.toJson(currency.getJsonPesent());
    }
}
