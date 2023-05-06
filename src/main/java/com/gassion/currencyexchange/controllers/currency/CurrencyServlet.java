package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.logic.CurrencyLogic;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CurrencyLogic.getCurrencyByCode(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
