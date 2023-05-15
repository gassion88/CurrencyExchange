package com.gassion.currencyexchange.controllers.exchangeRates;

import com.gassion.currencyexchange.entities.DTO.ExchangeRateDTO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.entities.factories.ExchangeRateFactory;
import com.gassion.currencyexchange.service.ExchangeRateService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.Strings;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ExchangeRatesServlet", value = "/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
    private static final Gson GSON = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<ExchangeRate> exchangeRate = ExchangeRateService.getAllExchangeRate();
            List<ExchangeRateDTO> exchangeRateDTOS = ValidateUtils.getDTOFormat(exchangeRate);
            String exchangeRateDTOSJson = GSON.toJson(exchangeRateDTOS);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateDTOSJson);
        } catch (Exception e) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.DATABASE_IS_NOT_AVAILABLE));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.addExchangeRateRequestValidate(request);
            ExchangeRate exchangeRate = ExchangeRateFactory.getInUrl(request.getParameterMap());
            String exchangeRateCode = request.getParameterMap().get("baseCurrencyCode")[0] + request.getParameterMap().get("targetCurrencyCode")[0];

            ExchangeRate addedExchangeRate = ExchangeRateService.addExchangeRate(exchangeRate,  exchangeRateCode);
            ExchangeRateDTO addedExchangeRateDTO = addedExchangeRate.getDTOFormat();
            String addedExchangeRateDTOJson = GSON.toJson(addedExchangeRateDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, addedExchangeRateDTOJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.ERROR));
        }
    }
}
