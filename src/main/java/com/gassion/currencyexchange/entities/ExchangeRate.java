package com.gassion.currencyexchange.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ExchangeRate extends Model {

    private final int id;

    private final int baseCurrencyId;

    private final int targetCurrencyId;

    private BigDecimal rate;
    
}
