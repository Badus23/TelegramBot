package com.mamchura.TelegramBot.services;

public interface BotSendMessageService {
    void sendMessage(String chatID, String message);
}
