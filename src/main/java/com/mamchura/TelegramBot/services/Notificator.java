package com.mamchura.TelegramBot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamchura.TelegramBot.models.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Notificator {
    private final BotSendMessageService botSendMessageService;
    private final TelegramUserService telegramUserService;
    private final CryptocurrencyService cryptocurrencyService;
    private final RestTemplate template = new RestTemplate();

    @Autowired
    public Notificator(BotSendMessageService botSendMessageService, TelegramUserService telegramUserService, CryptocurrencyService cryptocurrencyService) {
        this.botSendMessageService = botSendMessageService;
        this.telegramUserService = telegramUserService;
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @Scheduled(fixedRateString = "60000")
    public void checkUpdate() throws JsonProcessingException, InterruptedException {
        List<Cryptocurrency> currencies = cryptocurrencyService.retrieveAllCryptocurrencies();
        for (Cryptocurrency currency: currencies) {
            Thread.sleep(2000);
            String link = "https://api.binance.com/api/v3/ticker/price?symbol=%sUSDT";
            String response = template.getForObject(String.format(link, currency.getName()), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode obj = mapper.readTree(response);
            double result = (obj.get("price").asDouble());
            System.out.println(result);
            double result2 = result - currency.getExpectedRate();
            System.out.println(result2);
            if (result2 * currency.getExpectedAction() > 0) {
                botSendMessageService.sendMessage(currency.getOwner().getChatId() ,"We hasten to inform you that the " + currency.getName().toUpperCase() + " rate has exceeded the one you set." +
                        "\nNow it is " + String.format("%.3f", result));
                cryptocurrencyService.delete(currency);
            }
        }
    }
}
