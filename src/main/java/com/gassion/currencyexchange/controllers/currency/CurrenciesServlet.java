package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import com.gassion.currencyexchange.entities.factories.CurrencyFactory;
import com.gassion.currencyexchange.service.CurrencyService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.Strings;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CurrenciesServlet", value = "/currencies")
public class CurrenciesServlet extends HttpServlet {
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    private static final Gson GSON = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Currency> currencies = CurrencyService.getAllCurrenciesRequest();

            List<CurrencyDTO> currenciesDTO = ValidateUtils.getDTOFormat(currencies);
            String currenciesDTOJson = GSON.toJson(currenciesDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currenciesDTOJson);
        } catch (Exception e){
            OutResponse.setResponse(response, HttpServletResponse.SC_CONFLICT, GSON.toJson(Strings.ERROR));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.addCurrencyRequestValidate(request);
            Currency currency  = CurrencyFactory.getInUrl(request.getParameterMap());

            Currency addedCurrency = CurrencyService.addCurrency(currency);
            CurrencyDTO addedCurrencyDTO = addedCurrency.getDTOFormat();
            String addedCurrencyDTOJson = GSON.toJson(addedCurrencyDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, addedCurrencyDTOJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), e.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.ERROR));
        }
    }
}