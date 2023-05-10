package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.service.CurrencyService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.ValidateUtils;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.getCurrencyByCodeValidate(request);
            String currencyCode  = request.getPathInfo().split("/")[1];

            String currencyJson = CurrencyService.getCurrencyByCode(currencyCode);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyJson);
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CurrencyService.deleteCurrencyByCode(request, response);
    }
}
