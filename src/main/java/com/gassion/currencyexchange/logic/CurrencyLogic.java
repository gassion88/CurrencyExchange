package com.gassion.currencyexchange.logic;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.jsonResponse.CurrencyJson;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CurrencyLogic {
    private static final Gson GSON = new Gson();
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    private static final CurrencyDAO CURRENCY_DAO = new CurrencyDAO();
    public static void getAllCurrencies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Currency> currencies = CURRENCY_DAO.getAll();
        List<CurrencyJson> currenciesJson = VALIDATE_UTILS.getJsonFormat(currencies);
        String currenciesList = GSON.toJson(currenciesJson);
        OutResponse.getCurrencies(response, currenciesList);
    }


}
