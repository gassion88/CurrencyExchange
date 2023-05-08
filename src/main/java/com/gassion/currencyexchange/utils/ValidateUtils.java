package com.gassion.currencyexchange.utils;

import com.gassion.currencyexchange.DAO.JsonPresent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateUtils {
    public <T extends JsonPresent, J> List<J> getJsonFormat(List<T> t) {
        List<J> currencyJson = new ArrayList<>();

        for (T cur : t) {
            currencyJson.add((J) cur.getJsonPesent());
        }

        return currencyJson;
    }

    public void addCurrencyRequestValidate(HttpServletRequest request) throws SQLException {
        Map<String, String[]> params = request.getParameterMap();
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String sign = request.getParameter("sign");

        if(name == null || code == null || sign == null) {
            throw new SQLException("Required form field is missing", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }

        if (params.size() != 3) {
            throw new SQLException("Invalid number of parameters", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getCurrencyByCodeRequestValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("Код валюты отсутствует в адресе");
            out.flush();
            return;
        }

        if (pathParts.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("Invalid number of parameters");
            out.flush();
            return;
        }
    }

    public void addExchangeRateRequestValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Map<String, String[]> params = request.getParameterMap();
        String baseCurrencyCode = request.getParameter("baseCurrencyCode");
        String targetCurrencyCode = request.getParameter("targetCurrencyCode");
        String rate = request.getParameter("rate");

        if(baseCurrencyCode == null || targetCurrencyCode == null || rate == null) {
            OutResponse.setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Required form field is missing");

            return;
        }

        if (params.size() != 3) {
            OutResponse.setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid number of parameters");

            return;
        }
    }

    public static void getExchangeRateByCodeValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            OutResponse.setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Код валюты отсутствует в адресе");

            return;
        }

        if (pathParts.length != 2) {
            OutResponse.setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid number of parameters");

            return;
        }
    }

}
