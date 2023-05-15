package com.gassion.currencyexchange.entities.factories;

import com.gassion.currencyexchange.DAO.JsonPresent;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.MessageDTO;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;


public class MessageDTOFactory {
    private static final Gson GSON = new Gson();
    public static MessageDTO errorFactory (HttpServletResponse response, SQLException e) {
        return new MessageDTO(response, e.getMessage(), e.getErrorCode());
    }

    public static MessageDTO errorFactory (HttpServletResponse response, Exception e) {
        return new MessageDTO(response, e.getMessage(), 101);
    }

    public static MessageDTO goodResponseFactory(HttpServletResponse response, JsonPresent currenciesDTO) {
        return new MessageDTO(response, GSON.toJson(currenciesDTO.getDTOFormat()), HttpServletResponse.SC_OK);
    }

    public static MessageDTO goodResponseFactory(HttpServletResponse response, List<JsonPresent> currenciesDTO) {
        String jsonStr = GSON.toJson(ValidateUtils.getDTOFormat(currenciesDTO));

        return  new MessageDTO(response,  jsonStr, HttpServletResponse.SC_OK);
    }
}
