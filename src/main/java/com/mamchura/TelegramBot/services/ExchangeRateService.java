package com.mamchura.TelegramBot.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamchura.TelegramBot.bot.CustomBot;
import com.mamchura.TelegramBot.commands.Command;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ExchangeRateService implements Command {
    private final RestTemplate template = new RestTemplate();
    private final BotSendMessageService botsendMessageService;
    private final String link = "https://api.binance.com/api/v3/ticker/price?symbol=%sUSDT";

    public ExchangeRateService(BotSendMessageService botsendMessageService) {
        this.botsendMessageService = botsendMessageService;
    }

    @Override
    public void execute(Update update) {
        if (CustomBot.arguments != null) {
            try {
                String response = template.getForObject(String.format(link, CustomBot.arguments[1]), String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode obj = mapper.readTree(response);
                double result = (obj.get("price").asDouble());
                botsendMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format("%.3f",result));
            } catch (Exception e) {
                botsendMessageService.sendMessage(update.getMessage().getChatId().toString(), "Please, make sure you have correct abbreviation");
            } finally {
                CustomBot.arguments = null;
            }
        }
    }
}
