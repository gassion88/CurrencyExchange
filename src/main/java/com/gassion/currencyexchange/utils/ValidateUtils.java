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

    public void getCurrencyByCodeRequestValidate(HttpServletRequest request) throws SQLException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            throw new SQLException("Код валюты отсутствует в адресе", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }

        if (pathParts.length != 2) {
            throw new SQLException("Invalid number of parameters", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void addExchangeRateRequestValidate(HttpServletRequest request) throws SQLException {
        Map<String, String[]> params = request.getParameterMap();
        String baseCurrencyCode = request.getParameter("baseCurrencyCode");
        String targetCurrencyCode = request.getParameter("targetCurrencyCode");
        String rate = request.getParameter("rate");

        if(baseCurrencyCode == null || targetCurrencyCode == null || rate == null) {
            throw new SQLException("Required form field is missing", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }

        if (params.size() != 3) {
            throw new SQLException("Invalid number of parameters", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static void getExchangeRateByCodeValidate(HttpServletRequest request) throws SQLException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            throw new SQLException("Код валюты отсутствует в адресе", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }

        if (pathParts.length != 2) {
            throw new SQLException("Invalid number of parameters", "Error", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
