package com.mamchura.TelegramBot.services;

import com.mamchura.TelegramBot.models.TelegramUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TelegramUserService {
    void save(TelegramUser telegramUser);
    List<TelegramUser> retrieveAllActiveUsers();
    Optional<TelegramUser> findByChatId(String chatId);
}
