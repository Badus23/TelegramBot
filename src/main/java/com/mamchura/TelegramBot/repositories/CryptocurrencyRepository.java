package com.mamchura.TelegramBot.repositories;

import com.mamchura.TelegramBot.models.Cryptocurrency;
import com.mamchura.TelegramBot.models.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Integer> {
    List<Cryptocurrency> findAllByOwner(TelegramUser owner);
}
