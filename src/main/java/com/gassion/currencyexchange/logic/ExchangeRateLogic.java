package com.gassion.currencyexchange.logic;

import com.gassion.currencyexchange.DAO.ExchangeRateDAO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.entities.factories.ExchangeRateFactory;
import com.gassion.currencyexchange.entities.jsonResponse.ExchangeRateJson;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ExchangeRateLogic {
    private static final Gson GSON = new Gson();
    private static final ExchangeRateDAO EXCHANGE_RATE_DAO = new ExchangeRateDAO();
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    public static void getAllExchangeRate(HttpServletResponse response) throws IOException {
        try {
            List<ExchangeRate> exchangeRates = EXCHANGE_RATE_DAO.getAll();

            List<ExchangeRateJson> exchangeRatesJson = VALIDATE_UTILS.getJsonFormat(exchangeRates);
            String exchangeRatesJsonString = GSON.toJson(exchangeRatesJson);
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRatesJsonString);
        } catch (SQLException e) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database is not available");
        }
    }

    public static void addExchangeRate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.addExchangeRateRequestValidate(request);
            ExchangeRate exchangeRate = ExchangeRateFactory.getInUrl(request.getParameterMap());
            String exchangeRateCode = request.getParameterMap().get("baseCurrencyCode")[0] + request.getParameterMap().get("targetCurrencyCode")[0];

            EXCHANGE_RATE_DAO.add(exchangeRate);
            exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

            ExchangeRateJson exchangeRateJson = exchangeRate.getJsonPesent();
            String exchangeRateJsonString = GSON.toJson(exchangeRateJson);
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateJsonString);
        } catch (SQLException e) {
            if (e.getErrorCode() == HttpServletResponse.SC_BAD_REQUEST) {
                OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
            } else {
                OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, "Currency pair with this code already exists");
            }
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }

    public static void getExchangeRateByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.getExchangeRateByCodeValidate(request);
            String exchangeRateCode = request.getPathInfo().split("/")[1];
            ExchangeRate exchangeRate = EXCHANGE_RATE_DAO.get(exchangeRateCode);

            ExchangeRateJson exchangeRateJson = exchangeRate.getJsonPesent();
            String exchangeRateJsonString = GSON.toJson(exchangeRateJson);
            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateJsonString);
        } catch (SQLException e) {
            if (e.getErrorCode() == HttpServletResponse.SC_BAD_REQUEST) {
                OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
            } else {
                OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, "Currency pair with this code already exists");
            }
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }
}
