package com.cryptoexchanger.service;

import com.cryptoexchanger.dto.ExchangeCurrencyDTO;
import com.cryptoexchanger.dto.ExchangeRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBotService extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.chatId}")
    private String chatId;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
        }
    }

    public void sendMessage(ExchangeRequestDTO text) {
        SendMessage message = new SendMessage();
        String messageText = buildMessage(text);
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String buildMessage(ExchangeRequestDTO exchangeRequest) {
        ExchangeCurrencyDTO convertedCurrency = exchangeRequest.getConvertedCurrency();
        ExchangeCurrencyDTO currencyToConvert = exchangeRequest.getCurrencyToConvert();
        String recipientAddress = exchangeRequest.getRecipientAddress();

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Exchange request received:\n\n");
        messageBuilder.append("Converted Currency:\n")
                .append("Amount: ").append(convertedCurrency.getAmount()).append("\n")
                .append("Name: ").append(convertedCurrency.getName()).append("\n")
                .append("Currency To Convert:\n").append("\n\n")
                .append("Amount: ").append(currencyToConvert.getAmount()).append("\n")
                .append("Name: ").append(currencyToConvert.getName()).append("\n")
                .append("Symbol: ").append(currencyToConvert.getSymbol()).append("\n\n")
                .append("Recipient Address: ").append(recipientAddress);

        return messageBuilder.toString();
    }
}
