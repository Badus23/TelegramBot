package com.mamchura.TelegramBot.services;

import com.mamchura.TelegramBot.models.Cryptocurrency;
import com.mamchura.TelegramBot.models.TelegramUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CryptocurrencyService {
    void save(Cryptocurrency cryptocurrency);
    List<Cryptocurrency> retrieveAllCryptocurrencies();
    List<Cryptocurrency> findAllByUser(TelegramUser user);
    void delete(Cryptocurrency cryptocurrency);
}
