package com.gassion.currencyexchange.service;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.factories.CurrencyFactory;
import com.gassion.currencyexchange.entities.jsonResponse.CurrencyJson;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CurrencyService {
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

            Currency currency  = CurrencyFactory.getInUrl(request.getParameterMap());
            CURRENCY_DAO.add(currency);
            currency = CURRENCY_DAO.get(currency.getCode());

            String currencyJson = GSON.toJson(currency.getJsonPesent());
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyJson);
        } catch (SQLException e) {
            if (e.getErrorCode() == HttpServletResponse.SC_BAD_REQUEST || e.getErrorCode() == HttpServletResponse.SC_NOT_FOUND) {
                OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
            } else {
                OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, "Currency with this code already exists");
            }
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }

    public static void getCurrencyByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.getCurrencyByCodeRequestValidate(request);

            String currencyCode  = request.getPathInfo().split("/")[1];
            Currency currency = CURRENCY_DAO.get(currencyCode);

            String currencyJson = GSON.toJson(currency.getJsonPesent());
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyJson);
        } catch (SQLException e) {
            if (e.getErrorCode() == HttpServletResponse.SC_BAD_REQUEST) {
                OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
            } else {
                OutResponse.setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Currency not found");
            }
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }

    public static void deleteCurrencyByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.deleteCurrencyByCodeValidate(request);

            String currencyCode = request.getPathInfo().split("/")[1];
            Currency currency = CURRENCY_DAO.get(currencyCode);

            new CurrencyDAO().delete(currency.getId());

            CurrencyJson currencyJson = currency.getJsonPesent();
            String currencyJsonString = GSON.toJson(currencyJson);
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, "Deleted \n" + currencyJsonString);
        }catch (SQLException e) {
            if (e.getErrorCode() == HttpServletResponse.SC_BAD_REQUEST) {
                OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
            } else {
                OutResponse.setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Валюта не найдена");
            }
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }
}