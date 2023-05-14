package com.gassion.currencyexchange.entities.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class ExchangeDTO implements JsonPresent {
    private final CurrencyDTO baseCurrency;
    private final CurrencyDTO targetCurrency;
    private final BigDecimal rate;
    private final BigDecimal amount;
    private final BigDecimal convertedAmount;
}
