package com.mamchura.TelegramBot.commands;

import com.mamchura.TelegramBot.services.BotSendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    private final BotSendMessageService botSendMessageService;
    private final static String UNKNOWN_MESSAGE = "Unknown command. Write <b>/help</b> to see a list of available commands";

    public UnknownCommand(BotSendMessageService botSendMessageService) {
        this.botSendMessageService = botSendMessageService;
    }

    @Override
    public void execute(Update update) {
        botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
