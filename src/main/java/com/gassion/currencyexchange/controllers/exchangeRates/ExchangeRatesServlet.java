package com.gassion.currencyexchange.controllers.exchangeRates;

import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.entities.factories.ExchangeRateFactory;
import com.gassion.currencyexchange.service.ExchangeRateService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ExchangeRatesServlet", value = "/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String exchangeRatesJson = ExchangeRateService.getAllExchangeRate();

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRatesJson);
        } catch (Exception e) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database is not available");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.addExchangeRateRequestValidate(request);
            ExchangeRate exchangeRate = ExchangeRateFactory.getInUrl(request.getParameterMap());
            String exchangeRateCode = request.getParameterMap().get("baseCurrencyCode")[0] + request.getParameterMap().get("targetCurrencyCode")[0];

            String exchangeRateJsonString = ExchangeRateService.addExchangeRate(exchangeRate,  exchangeRateCode);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateJsonString);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }
}
