package com.gassion.currencyexchange.entities.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateDTO extends DTO{
    private final int id;
    private final CurrencyDTO baseCurrency;
    private final CurrencyDTO targetCurrency;
    private final BigDecimal rate;
}
