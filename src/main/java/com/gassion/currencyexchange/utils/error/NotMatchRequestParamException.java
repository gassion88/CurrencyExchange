package com.gassion.currencyexchange.utils.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotMatchRequestParamException extends RuntimeException {

    private String message;

    private int errorCode;

    public NotMatchRequestParamException(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
