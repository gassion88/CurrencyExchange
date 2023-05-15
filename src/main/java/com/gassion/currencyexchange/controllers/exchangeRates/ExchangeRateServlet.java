package com.gassion.currencyexchange.controllers.exchangeRates;

import com.gassion.currencyexchange.entities.DTO.ExchangeRateDTO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.service.ExchangeRateService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.Strings;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ExchangeRateServlet", value = "/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private static final Gson GSON = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.getExchangeRateByCodeValidate(request);
            String exchangeRateCode = request.getPathInfo().split("/")[1];

            ExchangeRate exchangeRate = ExchangeRateService.getExchangeRateByCodeRequest(exchangeRateCode);
            ExchangeRateDTO exchangeRateDTO = exchangeRate.getDTOFormat();
            String exchangeRateDTOJson = GSON.toJson(exchangeRateDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateDTOJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.ERROR));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("PATCH")) {
            this.doPatch(request, response);
            return;
        }

        super.service(request, response);
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.patchExchangeRateByCodeValidate(request);
            String exchangeRateCode = request.getPathInfo().split("/")[1];
            String rate = request.getParameterMap().get("rate")[0];

            ExchangeRate exchangeRate = ExchangeRateService.patchExchangeRateByCodeRequest(exchangeRateCode, rate);
            ExchangeRateDTO exchangeRateDTO = exchangeRate.getDTOFormat();
            String exchangeRateDTOJson = GSON.toJson(exchangeRateDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateDTOJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.ERROR));
        }
    }
}
