package com.gassion.currencyexchange.controllers.currency;

import com.gassion.currencyexchange.DAO.CurrencyDAO;
import com.gassion.currencyexchange.entities.Currency;
import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import com.gassion.currencyexchange.service.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.SQLException;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CurrencyServletTest {

    @Mock
    private CurrencyDAO currencyDAO;

    @Test
    void getCurrency() throws Exception {
        Currency currency1 = new Currency(1, "USD", "United States Dollar", "S");
        Currency currency2 = new Currency(2, "RUB", "Russian Federation Ruble", "R");
        Mockito.when(currencyDAO.getAll()).thenReturn(List.of(currency1, currency2));

        List<Currency> currencies = currencyDAO.getAll();
        Assertions.assertEquals(2, currencies.size());
    }

    @Test
    void doPost_Error_SQLException() {
        Currency currency1 = new Currency(0, "TestCode", "TestFullName", "Test");


    }

    @Test
    void doDelete() {
    }
}