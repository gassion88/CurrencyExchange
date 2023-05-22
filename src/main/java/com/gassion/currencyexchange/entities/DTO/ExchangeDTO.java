package com.gassion.currencyexchange.entities.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeDTO extends DTO{
    private final CurrencyDTO baseCurrency;
    private final CurrencyDTO targetCurrency;
    private final BigDecimal rate;
    private final BigDecimal amount;
    private final BigDecimal convertedAmount;
}
