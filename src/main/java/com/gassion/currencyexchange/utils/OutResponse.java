package com.gassion.currencyexchange.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class OutResponse {
    private static PrintWriter out;

    public static void constraintUniq(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        out = response.getWriter();
        out.print("Валюта с таким кодом уже существует");
        out.flush();
    }

    public static void errorDB(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        out = response.getWriter();
        out.print("Error");
        out.flush();
    }

    public static void addedCurrency(HttpServletResponse response, String jsonPresent) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        out = response.getWriter();
        out.print(jsonPresent);
        out.flush();
    }

    public static void getCurrencies(HttpServletResponse response, String currenciesList) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        out = response.getWriter();
        out.print(currenciesList);
        out.flush();
    }
}
