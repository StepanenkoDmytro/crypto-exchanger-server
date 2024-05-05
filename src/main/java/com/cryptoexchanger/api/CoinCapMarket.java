package com.cryptoexchanger.api;

import com.cryptoexchanger.api.helpers.RequestManager;
import com.cryptoexchanger.api.helpers.ResponseMapper;
import com.cryptoexchanger.api.producers.entity.coinCap.Coin;
import com.cryptoexchanger.api.producers.wrappers.CoinData;
import com.cryptoexchanger.api.producers.wrappers.CoinWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpResponse;
import java.util.List;

@Component
public class CoinCapMarket {
    private static final String BASE_URL_FOR_ASSETS = "https://api.coincap.io/v2/assets";
    private final RequestManager requestManager;
    private final ResponseMapper responseMapper;
    @Value("${coincap.api.key}")
    private String apiKey;

    @Autowired
    public CoinCapMarket(RequestManager requestManager, ResponseMapper responseMapper) {
        this.requestManager = requestManager;
        this.responseMapper = responseMapper;
    }

    public List<Coin> findAll(int page) {
        page = page - 1;
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_FOR_ASSETS)
                .queryParam("limit", RequestManager.PAGE_LIMIT)
                .queryParam("offset", page * RequestManager.PAGE_LIMIT)
                .build()
                .toUriString();
        HttpResponse<String> response = requestManager.sendHttpRequestWithHeaderApiKey(url, apiKey);
        return responseMapper.convertCustomResponse(response, CoinData.class).getData();
    }

    public List<Coin> findByFilter(String filter) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_FOR_ASSETS)
                .queryParam("search", filter)
                .build()
                .toUriString();

        HttpResponse<String> response = requestManager.sendHttpRequestWithHeaderApiKey(url, apiKey);
        return responseMapper.convertCustomResponse(response, CoinData.class).getData();
    }

    public Coin findByTicker(String ticker) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_FOR_ASSETS)
                .path("/{ticker}")
                .buildAndExpand(ticker)
                .toUriString();
        HttpResponse<String> response = requestManager.sendHttpRequestWithHeaderApiKey(url, apiKey);
        return responseMapper.convertCustomResponse(response, CoinWrapper.class).getData();
    }

//    public List<CoinDto> findByTikersList(List<String> coinsList) {
//        String coins = String.join(",", coinsList);
//        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_FOR_ASSETS)
//                .queryParam("ids", coins)
//                .build()
//                .toUriString();
//
//        HttpResponse<String> response = requestManager.sendHttpRequestWithHeaderApiKey(url, apiKey);
//        List<Coin> data = responseMapper.convertCustomResponse(response, CoinData.class).getData();
//        return data.stream()
//                .map(CoinDto::mapCoinToDto)
//                .toList();
//    }
//
//    public List<CandlesDto> findCandlesDataByBaseAndQuoteCoins(String baseID, String quoteID){
//        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_FOR_CANDLES)
//                .queryParam("exchange", "binance")
//                .queryParam("interval", "h8")
//                .queryParam("baseId", baseID)
//                .queryParam("quoteId", quoteID)
//                .build()
//                .toUriString();
//
//        HttpResponse<String> response = requestManager.sendHttpRequestWithHeaderApiKey(url, apiKey);
//        List<Candle> data = responseMapper.convertCustomResponse(response, CandlesData.class).getData();
////        return data.stream()
////                .map(CandlesDto::mapCandlesDto)
////                .toList();
//        return new ArrayList<>();
//    }
}
