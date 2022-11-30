package com.mamchura.TelegramBot.repositories;

import com.mamchura.TelegramBot.models.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Integer> {
    List<TelegramUser> findAllByActiveTrue();
    Optional<TelegramUser> findByChatId(String chatId);
}
