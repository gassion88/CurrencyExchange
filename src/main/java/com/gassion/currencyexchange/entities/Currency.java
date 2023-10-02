package com.gassion.currencyexchange.entities;

import com.gassion.currencyexchange.entities.DTO.CurrencyDTO;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Currency extends Model {

    private final int id;

    private String code;

    private String fullName;

    private String sign;

}
