package com.gassion.currencyexchange.entities.DTO.DTOFactories;

import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import com.gassion.currencyexchange.entities.DTO.DTO;
import com.gassion.currencyexchange.entities.Model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDTOFactory extends DTOFactory{
    @Override
    public <M extends Model> DTO getFromModel(M model) {
        Currency currency = (Currency) model;
        return new CurrencyDTO(currency.getId(), currency.getCode(), currency.getFullName(), currency.getSign());
    }

    public List<CurrencyDTO> getFromModel(List<Currency> model) {
        List<CurrencyDTO> currencies = new ArrayList<>();

        for (Currency currency : model) {
            currencies.add((CurrencyDTO) getFromModel(currency));
        }

        return currencies;
    }
}
