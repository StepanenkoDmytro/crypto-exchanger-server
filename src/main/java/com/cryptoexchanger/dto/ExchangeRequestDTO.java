package com.cryptoexchanger.dto;

import lombok.Data;

@Data
public class ExchangeRequestDTO {
    private ExchangeCurrencyDTO currencyFrom;
    private ExchangeCurrencyDTO currencyTo;
    private String recipientAddress;
}
