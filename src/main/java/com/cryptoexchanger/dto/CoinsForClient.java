package com.cryptoexchanger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CoinsForClient {
    List<CoinDto> data;
    int totalPages;
    int totalItems;
    int currentPage;
}
