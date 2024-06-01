package com.cryptoexchanger.rest;

import com.cryptoexchanger.dto.CoinDto;
import com.cryptoexchanger.dto.CoinsForClient;
import com.cryptoexchanger.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crypto")
@CrossOrigin
public class CryptoController {
    private final CoinService coinService;

    @Autowired
    public CryptoController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("")
    public ResponseEntity<CoinsForClient> getAllCoins(@RequestParam(required = false, defaultValue = "") String filter,
                                                      @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        CoinsForClient coins;

        if (StringUtils.isEmpty(filter)) {
            coins = coinService.getAllCoins(currentPage);
        } else {
            coins = coinService.getCoinsByFilter(currentPage, filter);
        }

        return ResponseEntity.ok(coins);
    }

    @GetMapping("/list")
    public List<CoinDto> getCoinsByTickerList(@RequestParam List<String> tickers) {
        List<CoinDto> byTickerList = coinService.getByTickerList(tickers);
        return byTickerList;
    }
}
