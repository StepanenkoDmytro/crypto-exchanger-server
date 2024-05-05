package com.cryptoexchanger.api.producers.wrappers;

import com.cryptoexchanger.api.producers.entity.coinCap.Coin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoinWrapper {
    @JsonProperty("data")
    private Coin data;
    @JsonProperty("timestamp")
    private long timestamp;
}
