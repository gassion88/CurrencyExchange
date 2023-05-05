package com.gassion.currencyexchange.logic;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CurrencyLogic {
    private static final Gson GSON = new Gson();

    public static void getAllCurrencies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Currency> currencies = new CurrencyDAO().getAll();
        String currenciesList = GSON.toJson(currencies);
        System.out.println("133");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(currenciesList);
        out.flush();
    }

    public static void addCurrency(HttpServletRequest request, HttpServletResponse response) {

    }
}
