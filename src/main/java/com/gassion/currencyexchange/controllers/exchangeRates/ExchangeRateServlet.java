package com.gassion.currencyexchange.controllers.exchangeRates;

import com.gassion.currencyexchange.service.ExchangeRateService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ExchangeRateServlet", value = "/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ValidateUtils.getExchangeRateByCodeValidate(request);
            String exchangeRateCode = request.getPathInfo().split("/")[1];

            String exchangeRateJson = ExchangeRateService.getExchangeRateByCodeRequest(exchangeRateCode);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
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

            String exchangeRateJson = ExchangeRateService.patchExchangeRateByCodeRequest(exchangeRateCode, rate);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, exchangeRateJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }
}
