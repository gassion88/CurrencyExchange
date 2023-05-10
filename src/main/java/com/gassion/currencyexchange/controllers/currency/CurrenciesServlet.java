package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.factories.CurrencyFactory;
import com.gassion.currencyexchange.service.CurrencyService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CurrenciesServlet", value = "/currencies")
public class CurrenciesServlet extends HttpServlet {
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String currenciesJson = CurrencyService.getAllCurrenciesRequest();

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currenciesJson);
        } catch (Exception e){
            OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, "Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.addCurrencyRequestValidate(request);
            Currency currency  = CurrencyFactory.getInUrl(request.getParameterMap());

            String currencyJson = CurrencyService.addCurrency(currency);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }
}