package com.gassion.currencyexchange.entities.factories;

import com.gassion.currencyexchange.entities.Currency;

import java.util.Map;

public class CurrencyFactory {
    public static Currency getInUrl(Map<String, String[]> parameterMap) {
        String name = parameterMap.get("name")[0];
        String code = parameterMap.get("code")[0];
        String sign = parameterMap.get("sign")[0];

        return new Currency(0, code, name, sign);
    }
}
