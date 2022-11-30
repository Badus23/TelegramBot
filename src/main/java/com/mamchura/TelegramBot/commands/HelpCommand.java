package com.mamchura.TelegramBot.commands;

import com.mamchura.TelegramBot.services.BotSendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {

    private final BotSendMessageService botSendMessageService;
    private final static String HELP_MESSAGE = """
            <b>Available commands</b>

            <b>Start/Stop the bot</b>
            /start - To make your status "active"\s
            /stop - To make your status "inactive"\s
            /stat - To show people using this bot\s
            /help - for help
            """;

    public HelpCommand(BotSendMessageService botSendMessageService) {
        this.botSendMessageService = botSendMessageService;
    }

    @Override
    public void execute(Update update) {
        botSendMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
