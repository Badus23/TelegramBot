package com.mamchura.TelegramBot.commands;

import com.google.common.collect.ImmutableMap;
import com.mamchura.TelegramBot.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mamchura.TelegramBot.commands.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(BotSendMessageService sendBotMessageService, TelegramUserService telegramUserService, CryptocurrencyService cryptocurrencyService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(UNKNOWN.getCommandName(), new UnknownCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(RATE.getCommandName(), new ExchangeRateService(sendBotMessageService))
                .put(NOTIFICATION.getCommandName(), new RateNotificationService(sendBotMessageService, telegramUserService, cryptocurrencyService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
