package com.gassion.currencyexchange.controllers;

import com.gassion.currencyexchange.service.ExchangeService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ExchangeServlet", value = "/exchange/*")
public class ExchangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.exchangeValidate(request);
            String baseCurrencyCode = request.getParameterMap().get("from")[0];
            String targetCurrencyCode = request.getParameterMap().get("to")[0];
            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(request.getParameterMap().get("amount")[0]));

            String exchangeJson = ExchangeService.exchange(baseCurrencyCode, targetCurrencyCode, amount);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeJson);
        } catch (Exception e) {
            OutResponse.setResponse(response, HttpServletResponse.SC_NOT_FOUND, """
                                                                            {
                                                                                "message": "Валюта не найдена"
                                                                            }""");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
