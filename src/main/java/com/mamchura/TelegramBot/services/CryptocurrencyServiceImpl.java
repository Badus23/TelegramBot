package com.mamchura.TelegramBot.services;

import com.mamchura.TelegramBot.models.Cryptocurrency;
import com.mamchura.TelegramBot.models.TelegramUser;
import com.mamchura.TelegramBot.repositories.CryptocurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {
    private final CryptocurrencyRepository repository;

    @Autowired
    public CryptocurrencyServiceImpl(CryptocurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Cryptocurrency cryptocurrency) {
        repository.save(cryptocurrency);
    }

    @Override
    public List<Cryptocurrency> retrieveAllCryptocurrencies() {
        return repository.findAll();
    }

    @Override
    public List<Cryptocurrency> findAllByUser(TelegramUser user) {
        return repository.findAllByOwner(user);
    }

    @Override
    public void delete(Cryptocurrency cryptocurrency) {
        repository.delete(cryptocurrency);
    }
}
