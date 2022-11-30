package com.mamchura.TelegramBot.commands;

import com.mamchura.TelegramBot.services.BotSendMessageService;
import com.mamchura.TelegramBot.services.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    private final BotSendMessageService botSendMessageService;
    private final TelegramUserService telegramUserService;
    private final static String STOP_MESSAGE = "Your status is <b>Inactive</b> now";

    public StopCommand(BotSendMessageService botSendMessageService, TelegramUserService telegramUserService) {
        this.botSendMessageService = botSendMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        telegramUserService.findByChatId(update.getMessage().getChatId().toString())
                .ifPresent(it -> {
                    it.setActive(false);
                    telegramUserService.save(it);
                });
    }
}
