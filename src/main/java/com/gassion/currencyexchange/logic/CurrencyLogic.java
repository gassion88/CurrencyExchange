package com.gassion.currencyexchange.logic;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.jsonResponse.CurrencyJson;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class CurrencyLogic {
    private static final Gson GSON = new Gson();
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();

    public static void getAllCurrencies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Currency> currencies = new CurrencyDAO().getAll();
        List<CurrencyJson> currenciesJson = ValidateUtils.getJsonFormat(currencies);
        String currenciesList = GSON.toJson(currenciesJson);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(currenciesList);
        out.flush();
    }

    public static void addCurrency(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VALIDATE_UTILS.addCurrencyRequestValidate(request, response);

        Currency currency  = Currency.currencyFactory(request.getParameterMap());

        try {
            new CurrencyDAO().add(currency);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
