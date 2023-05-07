package com.gassion.currencyexchange.entities;

import com.gassion.currencyexchange.DAO.JsonPresent;
import com.gassion.currencyexchange.entities.jsonResponse.CurrencyJson;

import java.util.Map;
import java.util.Objects;

public class Currency implements JsonPresent<CurrencyJson> {
    private final int id;
    private String code;
    private String fullName;
    private String sign;

    public Currency(int id, String code, String fullName, String sign) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
    }

    public static Currency currencyFactory(Map<String, String[]> parameterMap) {
        String name = parameterMap.get("name")[0];
        String code = parameterMap.get("code")[0];
        String sign = parameterMap.get("sign")[0];

        return new Currency(0, code, name, sign);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (id != currency.id) return false;
        if (!Objects.equals(code, currency.code)) return false;
        if (!Objects.equals(fullName, currency.fullName)) return false;
        return Objects.equals(sign, currency.sign);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    @Override
    public CurrencyJson getJsonPesent() {
        return new CurrencyJson(getId(), getCode(), getFullName(), getSign());
    }
}
