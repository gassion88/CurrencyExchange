package com.gassion.currencyexchange.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class OutResponse {
    private static PrintWriter out;

    public static void setResponse(HttpServletResponse response, int statusCode, String resource) throws IOException {
        response.setStatus(statusCode);
        out = response.getWriter();
        out.print(resource);
        out.flush();
    }
}
