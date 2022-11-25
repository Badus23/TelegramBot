package com.mamchura.TelegramBot.services;

import com.mamchura.TelegramBot.bot.CustomBot;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotSendMessageServiceImpl implements BotSendMessageService {

    private final CustomBot bot;

    @Autowired
    public BotSendMessageServiceImpl(CustomBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatID, String message) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatID);
        sm.setText(message);
        sm.enableHtml(true);

        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
