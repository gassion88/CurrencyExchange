package com.gassion.currencyexchange.controllers;

import com.gassion.currencyexchange.entities.DTO.ExchangeDTO;
import com.gassion.currencyexchange.service.ExchangeService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ExchangeServlet", value = "/exchange/*")
public class ExchangeServlet extends HttpServlet {
    private static final Gson GSON = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.exchangeValidate(request);
            String baseCurrencyCode = request.getParameterMap().get("from")[0];
            String targetCurrencyCode = request.getParameterMap().get("to")[0];
            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(request.getParameterMap().get("amount")[0]));

            ExchangeDTO exchangeDTO = ExchangeService.exchange(baseCurrencyCode, targetCurrencyCode, amount);
            String exchangeDTOJson = GSON.toJson(exchangeDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeDTOJson);
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
