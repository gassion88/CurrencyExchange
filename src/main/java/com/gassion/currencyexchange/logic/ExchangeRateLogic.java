package com.gassion.currencyexchange.logic;

import com.gassion.currencyexchange.DAO.ExchangeRateDAO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.entities.jsonResponse.ExchangeRateJson;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ExchangeRateLogic {
    private static final Gson GSON = new Gson();
    private static final ExchangeRateDAO EXCHANGE_RATE_DAO = new ExchangeRateDAO();
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    public static void getAllExchangeRate(HttpServletResponse response) throws IOException {
        List<ExchangeRate> exchangeRates = EXCHANGE_RATE_DAO.getAll();
        List<ExchangeRateJson> exchangeRatesJson = VALIDATE_UTILS.getJsonFormat(exchangeRates);
        String exchangeRatesJsonString = GSON.toJson(exchangeRatesJson);

        OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRatesJsonString);
    }
}
