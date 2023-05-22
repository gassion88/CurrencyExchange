package com.gassion.currencyexchange.entities.DTO.DTOFactories;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import com.gassion.currencyexchange.entities.DTO.DTO;
import com.gassion.currencyexchange.entities.DTO.ExchangeRateDTO;
import com.gassion.currencyexchange.entities.ExchangeRate;
import com.gassion.currencyexchange.entities.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateDTOFactory extends DTOFactory{
    @Override
    public <M extends Model> DTO getFromModel(M model) {
        ExchangeRate exchangeRate = (ExchangeRate) model;

        CurrencyDTO baseCurrencyDTO;
        CurrencyDTO targetCurrencyDTO;
        try {
            Currency baseCurrency = new CurrencyDAO().getById(exchangeRate.getBaseCurrencyId());
            Currency targetCurrency = new CurrencyDAO().getById(exchangeRate.getTargetCurrencyId());
            baseCurrencyDTO = (CurrencyDTO) new CurrencyDTOFactory().getFromModel(baseCurrency);
            targetCurrencyDTO = (CurrencyDTO) new CurrencyDTOFactory().getFromModel(targetCurrency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ExchangeRateDTO(exchangeRate.getId(), baseCurrencyDTO, targetCurrencyDTO, exchangeRate.getRate());
    }

    public List<ExchangeRateDTO> getFromModel(List<ExchangeRate> exchangeRates) {
        List<ExchangeRateDTO> exchangeRatesDTO = new ArrayList<>();

        for (ExchangeRate exchangeRate : exchangeRates) {
            ExchangeRateDTO exchangeRateDTO = (ExchangeRateDTO) new ExchangeRateDTOFactory().getFromModel(exchangeRates);
            exchangeRatesDTO.add(exchangeRateDTO);
        }

        return exchangeRatesDTO;
    }
}
