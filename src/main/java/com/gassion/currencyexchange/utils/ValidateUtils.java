package com.gassion.currencyexchange.utils;

import com.gassion.currencyexchange.DAO.JsonPresent;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.jsonResponse.CurrencyJson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
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

    public void addCurrencyRequestValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Map<String, String[]> params = request.getParameterMap();
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String sign = request.getParameter("sign");

        if(name == null || code == null || sign == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("Required form field is missing");
            out.flush();
            return;
        }

        if (params.size() != 3) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("Invalid number of parameters");
            out.flush();
            return;
        }
    }
}
