package com.cryptoexchanger.api.producers.wrappers;

import com.cryptoexchanger.api.producers.entity.coinCap.Coin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CoinData {
    @JsonProperty("data")
    private List<Coin> data;
    @JsonProperty("timestamp")
    private long timestamp;

    @Override
    public String toString() {
        return "{" +
                "data:" + data +
                ", timestamp:" + timestamp +
                '}';
    }
}
