package com.mamchura.TelegramBot.commands;

import com.mamchura.TelegramBot.services.BotSendMessageService;
import com.mamchura.TelegramBot.services.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command {
    private final TelegramUserService telegramUserService;
    private final BotSendMessageService sendBotMessageService;

    public final static String STAT_MESSAGE = "%s people use this bot.";

    @Autowired
    public StatCommand(BotSendMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE, activeUserCount));
    }
}
