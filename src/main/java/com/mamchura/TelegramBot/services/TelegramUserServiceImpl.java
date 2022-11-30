package com.mamchura.TelegramBot.services;

import com.mamchura.TelegramBot.models.TelegramUser;
import com.mamchura.TelegramBot.repositories.TelegramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService{

    private final TelegramUserRepository repository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        repository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return repository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return repository.findByChatId(chatId);
    }
}
