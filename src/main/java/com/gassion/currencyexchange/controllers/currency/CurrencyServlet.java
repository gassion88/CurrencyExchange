package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import com.gassion.currencyexchange.entities.DTO.DTOFactories.CurrencyDTOFactory;
import com.gassion.currencyexchange.service.CurrencyService;
import com.gassion.currencyexchange.utils.OutResponse;
import com.gassion.currencyexchange.utils.Strings;
import com.gassion.currencyexchange.utils.ValidateUtils;
import com.gassion.currencyexchange.utils.error.NotMatchRequestParamException;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
    private static final ValidateUtils VALIDATE_UTILS = new ValidateUtils();
    private static final Gson GSON = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.getCurrencyByCodeValidate(request);
            String currencyCode  = request.getPathInfo().split("/")[1];

            Currency currency = CurrencyService.getCurrencyByCodeRequest(currencyCode);
            CurrencyDTO currencyDTO = (CurrencyDTO) new CurrencyDTOFactory().getFromModel(currency);
            String currencyDTOJson = GSON.toJson(currencyDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyDTOJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), GSON.toJson(e.getMessage()));
        } catch (NotMatchRequestParamException s) {
            OutResponse.setResponse(response, s.getErrorCode(), s.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.ERROR));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            VALIDATE_UTILS.deleteCurrencyByCodeValidate(request);
            String currencyCode = request.getPathInfo().split("/")[1];

            Currency currency =  CurrencyService.deleteCurrencyByCodeRequest(currencyCode);
            CurrencyDTO currencyDTO = (CurrencyDTO) new CurrencyDTOFactory().getFromModel(currency);
            String currencyDTOJson = GSON.toJson(currencyDTO);

            OutResponse.setResponse(response, HttpServletResponse.SC_OK, currencyDTOJson);
        } catch (SQLException e) {
            OutResponse.setResponse(response, e.getErrorCode(), GSON.toJson(e.getMessage()));
        }  catch (NotMatchRequestParamException s) {
            OutResponse.setResponse(response, s.getErrorCode(), s.getMessage());
        } catch (Exception s) {
            OutResponse.setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, GSON.toJson(Strings.ERROR));
        }
    }
}
