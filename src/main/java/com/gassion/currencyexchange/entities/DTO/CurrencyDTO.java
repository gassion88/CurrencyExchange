package com.gassion.currencyexchange.entities.DTO;

import lombok.*;

@Data
public class CurrencyDTO implements JsonPresent {
    private final int id;
    private final String code;
    private  final String name;
    private final String sign;
}
