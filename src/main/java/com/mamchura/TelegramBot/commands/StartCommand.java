package com.mamchura.TelegramBot.commands;

import com.mamchura.TelegramBot.models.TelegramUser;
import com.mamchura.TelegramBot.services.BotSendMessageService;
import com.mamchura.TelegramBot.services.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final BotSendMessageService botSendMessageService;
    private final TelegramUserService telegramUserService;
    private final static String START_MESSAGE = "Your status is <b>Active</b> now";

    public StartCommand(BotSendMessageService botSendMessageService, TelegramUserService telegramUserService) {
        this.botSendMessageService = botSendMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        botSendMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
