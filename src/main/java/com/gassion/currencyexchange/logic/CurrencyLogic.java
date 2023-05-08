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
        try {
            List<Currency> currencies = CURRENCY_DAO.getAll();
            List<CurrencyJson> currenciesJson = VALIDATE_UTILS.getJsonFormat(currencies);
            String currenciesList = GSON.toJson(currenciesJson);
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currenciesList);
        } catch (SQLException e){
            OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, "Error");
        }
    }

    public static void addCurrency(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.addCurrencyRequestValidate(request);

            Currency currency  = Currency.currencyFactory(request.getParameterMap());
            CURRENCY_DAO.add(currency);
            currency = CURRENCY_DAO.get(currency.getCode());

            String currencyJson = GSON.toJson(currency.getJsonPesent());
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyJson);
        } catch (SQLException e) {
            if (e.getErrorCode() == HttpServletResponse.SC_BAD_REQUEST) {
                OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
            } else {
                OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, "Валюта с таким кодом уже существует");
            }
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }

    public static void getCurrencyByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VALIDATE_UTILS.getCurrencyByCodeRequestValidate(request, response);

        if(response.getStatus() != 200) {
            return;
        }

        String currencyCode  = request.getPathInfo().split("/")[1];
        Currency currency;

        try {
            currency = CURRENCY_DAO.get(currencyCode);
            String currencyJson = GSON.toJson(currency.getJsonPesent());
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Валюта не найдена");
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }
}
