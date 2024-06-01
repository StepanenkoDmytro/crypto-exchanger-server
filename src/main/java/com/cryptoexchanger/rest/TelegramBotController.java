package com.cryptoexchanger.rest;

import com.cryptoexchanger.dto.ExchangeRequestDTO;
import com.cryptoexchanger.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/exchange")
@CrossOrigin
public class TelegramBotController {

    @Autowired
    private TelegramBotService telegramBotService;

    @PostMapping("/submit")
    public ResponseEntity sendMessage(@RequestBody ExchangeRequestDTO request) {
        telegramBotService.sendMessage(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
