package com.cryptoexchanger.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeCurrencyDTO {
    private BigDecimal amount;
    private String id;
    private String name;
    private BigDecimal price;
    private String symbol;
}
