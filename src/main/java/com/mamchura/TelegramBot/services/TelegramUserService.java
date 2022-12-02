package com.mamchura.TelegramBot.services;

import com.mamchura.TelegramBot.models.TelegramUser;
import com.mamchura.TelegramBot.repositories.TelegramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserService {
    private final TelegramUserRepository repository;

    @Autowired
    public TelegramUserService(TelegramUserRepository repository) {
        this.repository = repository;
    }

    public void save(TelegramUser telegramUser) {
        repository.save(telegramUser);
    }

    public List<TelegramUser> retrieveAllActiveUsers() {
        return repository.findAllByActiveTrue();
    }

    public Optional<TelegramUser> findByChatId(String chatId) {
        return repository.findByChatId(chatId);
    }
}
