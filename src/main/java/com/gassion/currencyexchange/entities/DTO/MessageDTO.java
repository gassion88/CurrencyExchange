package com.gassion.currencyexchange.entities.DTO;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class MessageDTO {
    private final HttpServletResponse response;
    private final String message;
    private final int statusCode;
}
