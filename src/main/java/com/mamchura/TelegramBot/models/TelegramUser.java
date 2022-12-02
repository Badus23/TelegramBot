package com.mamchura.TelegramBot.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tg_user")
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "owner")
    private Set<Cryptocurrency> cryptocurrencies;

    public TelegramUser() {
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Cryptocurrency> getCryptocurrencies() {
        return cryptocurrencies;
    }

    public void setCryptocurrencies(Set<Cryptocurrency> cryptocurrencies) {
        this.cryptocurrencies = cryptocurrencies;
    }
}
