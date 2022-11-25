package com.mamchura.TelegramBot.bot;

import com.mamchura.TelegramBot.commands.CommandContainer;
import com.mamchura.TelegramBot.commands.CommandName;
import com.mamchura.TelegramBot.services.BotSendMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.mamchura.TelegramBot.commands.CommandName.NO;

@Component
public class CustomBot extends TelegramLongPollingBot {

    @Value("${bot_username}")
    private String username;

    @Value("${bot_token}")
    private String token;

    private final String PREFIX = "/";

    private final CommandContainer commandContainer;

    public CustomBot() {
        this.commandContainer = new CommandContainer(new BotSendMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
