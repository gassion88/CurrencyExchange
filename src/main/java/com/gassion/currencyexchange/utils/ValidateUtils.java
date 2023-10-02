package com.gassion.currencyexchange.utils;

import com.gassion.currencyexchange.utils.error.NotMatchRequestParamException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateUtils {
    public void addCurrencyRequestValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        Map<String, String[]> params = request.getParameterMap();
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String sign = request.getParameter("sign");

        if(name == null || code == null || sign == null) {
            throw new NotMatchRequestParamException(Strings.REQUIRED_FORM_FIELD_IS_MISSING, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (params.size() != 3) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getCurrencyByCodeValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            throw new NotMatchRequestParamException(Strings.THE_CURRENCY_CODE_IS_NOT_IN_THE_ADDRESS, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (pathParts.length != 2) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static void addExchangeRateRequestValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        Map<String, String[]> params = request.getParameterMap();
        String baseCurrencyCode = request.getParameter("baseCurrencyCode");
        String targetCurrencyCode = request.getParameter("targetCurrencyCode");
        String rate = request.getParameter("rate");

        if(baseCurrencyCode == null || targetCurrencyCode == null || rate == null) {
            throw new NotMatchRequestParamException(Strings.REQUIRED_FORM_FIELD_IS_MISSING, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (params.size() != 3) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static void getExchangeRateByCodeValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            throw new NotMatchRequestParamException(Strings.THE_CURRENCY_CODE_IS_NOT_IN_THE_ADDRESS, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (pathParts.length != 2) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static void patchExchangeRateByCodeValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            throw new NotMatchRequestParamException(Strings.REQUIRED_FORM_FIELD_IS_MISSING, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (pathParts.length != 2) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void deleteCurrencyByCodeValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String CurrencyCode = pathParts[1];

        if(CurrencyCode == null) {
            throw new NotMatchRequestParamException(Strings.REQUIRED_FORM_FIELD_IS_MISSING, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (pathParts.length != 2) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static void exchangeValidate(HttpServletRequest request) throws NotMatchRequestParamException {
        Map<String, String[]> params = request.getParameterMap();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(request.getParameter("amount")));

        if(from == null || to == null || amount.equals(BigDecimal.valueOf(0))) {
            throw new NotMatchRequestParamException(Strings.REQUIRED_FORM_FIELD_IS_MISSING, HttpServletResponse.SC_BAD_REQUEST);
        }

        if (params.size() != 3) {
            throw new NotMatchRequestParamException(Strings.INVALID_NUMBER_OF_PARAMETERS, HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
