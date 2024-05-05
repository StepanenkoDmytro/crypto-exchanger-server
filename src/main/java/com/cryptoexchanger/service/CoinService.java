package com.cryptoexchanger.service;

import com.cryptoexchanger.api.CoinCapMarket;
import com.cryptoexchanger.api.helpers.RequestManager;
import com.cryptoexchanger.api.producers.entity.coinCap.Coin;
import com.cryptoexchanger.dto.CoinDto;
import com.cryptoexchanger.dto.CoinsForClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {
    private final CoinCapMarket coinCapMarket;

    @Autowired
    public CoinService(CoinCapMarket coinCapMarket) {
        this.coinCapMarket = coinCapMarket;
    }

    public CoinsForClient getAllCoins(int page) {
        List<Coin> coinList = coinCapMarket.findAll(page);
        List<CoinDto> data = coinList.stream()
                .map(CoinDto::mapCoinToDto)
                .toList();

        return new CoinsForClient(data, RequestManager.MAX_PAGES, RequestManager.MAX_ELEMENTS, page);
    }

    public CoinsForClient getCoinsByFilter(int page, String filter) {
        List<Coin> coinList = coinCapMarket.findByFilter(filter);
        int totalPages = (int) Math.ceil((double) coinList.size() / RequestManager.PAGE_LIMIT);

        int startIndex = (page - 1) * RequestManager.PAGE_LIMIT;
        int endIndex = Math.min(RequestManager.PAGE_LIMIT, coinList.size() - startIndex);

        List<CoinDto> data = coinList.stream()
                .skip(startIndex)
                .limit(endIndex)
                .map(CoinDto::mapCoinToDto)
                .toList();

        return new CoinsForClient(data, totalPages, coinList.size(), page);
    }

    public CoinDto getByTicker(String ticker) {
        Coin coin = coinCapMarket.findByTicker(ticker);
        return CoinDto.mapCoinToDto(coin);
    }
}
