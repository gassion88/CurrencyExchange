package com.gassion.currencyexchange.service;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

public class CurrencyService {
    private static final CurrencyDAO CURRENCY_DAO = new CurrencyDAO();
    public static List<Currency> getAllCurrenciesRequest() throws Exception {
            return CURRENCY_DAO.getAll();
    }

    public static Currency addCurrency(Currency currency) throws SQLException {
        try {
            CURRENCY_DAO.add(currency);

            return CURRENCY_DAO.get(currency.getCode());
        } catch (SQLException e) {
            throw new SQLException("Currency with this code already exists", "Error", HttpServletResponse.SC_CONFLICT);
        }
    }

    public static Currency getCurrencyByCodeRequest(String currencyCode) throws Exception {
        Currency currency = CURRENCY_DAO.get(currencyCode);

        if (currency == null) {
            throw new SQLException("Currency not found", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        return currency;
    }

    public static Currency deleteCurrencyByCodeRequest(String currencyCode) throws Exception {
        Currency currency = CURRENCY_DAO.get(currencyCode);

        if (currency == null) {
            throw new SQLException("Currency not found", "Error", HttpServletResponse.SC_NOT_FOUND);
        }

        new CurrencyDAO().delete(currency.getId());

        return currency;
    }
}
