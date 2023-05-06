package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.logic.CurrencyLogic;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CurrenciesServlet", value = "/currencies")
public class CurrenciesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CurrencyLogic.getAllCurrencies(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CurrencyLogic.addCurrency(request, response);
    }
}