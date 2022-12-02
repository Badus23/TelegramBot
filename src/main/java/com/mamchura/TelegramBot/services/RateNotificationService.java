package com.mamchura.TelegramBot.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamchura.TelegramBot.bot.CustomBot;
import com.mamchura.TelegramBot.commands.Command;
import com.mamchura.TelegramBot.exceptions.UserNotFoundException;
import com.mamchura.TelegramBot.models.Cryptocurrency;
import com.mamchura.TelegramBot.models.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateNotificationService implements Command {
    private final BotSendMessageService botsendMessageService;
    private final CryptocurrencyService cryptocurrencyService;
    private final TelegramUserService userService;

    public RateNotificationService(BotSendMessageService botsendMessageService, TelegramUserService userService,
                                   CryptocurrencyService cryptocurrencyService) {
        this.botsendMessageService = botsendMessageService;
        this.userService = userService;
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @Override
    public void execute(Update update) {
        List<Cryptocurrency> cryptocurrency = new LinkedList<>();
        try {
            cryptocurrency = cryptocurrencyService.findAllByUser(userService.findByChatId(update.getMessage().getChatId().toString()).get());
        } catch (UserNotFoundException e) {
            cryptocurrency = new LinkedList<>();
        } finally {
            Cryptocurrency cryptocurrency1;
            if (cryptocurrency.size() > 0 && cryptocurrency.stream().anyMatch(e -> e.getName().equals(CustomBot.arguments[1]))) {
                cryptocurrency1 = cryptocurrency.stream().filter(e -> e.getName().equals(CustomBot.arguments[1])).toList().get(0);
                cryptocurrencyService.delete(cryptocurrency1);
            }
            int action = CustomBot.arguments[3].equalsIgnoreCase("up") ? 1 : -1;
            cryptocurrency1 = new Cryptocurrency(CustomBot.arguments[1], Double.parseDouble(CustomBot.arguments[2]), action);
            cryptocurrency1.setOwner(userService.findByChatId(update.getMessage().getChatId().toString()).get());
            cryptocurrencyService.save(cryptocurrency1);
            userService.save(userService.findByChatId(update.getMessage().getChatId().toString()).get());
            botsendMessageService.sendMessage(update.getMessage().getChatId().toString(), "Operation complete!");
        }
    }
}
