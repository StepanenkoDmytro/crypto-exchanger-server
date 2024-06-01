package com.cryptoexchanger.dto;

import lombok.Data;

@Data
public class ExchangeRequestDTO {
    private ExchangeCurrencyDTO convertedCurrency;
    private ExchangeCurrencyDTO currencyToConvert;
    private String recipientAddress;
}
