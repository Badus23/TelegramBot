package com.mamchura.TelegramBot.commands;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STAT("/stat"),
    RATE("/rate"),
    NOTIFICATION("/notification"),
    UNKNOWN("");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
