package com.mamchura.TelegramBot.models;

import javax.persistence.*;

@Entity
@Table(name = "cryptocurrencies")
public class Cryptocurrency {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ex_rate")
    private double expectedRate;

    @Column(name = "action")
    private int expectedAction;

    @ManyToOne
    @JoinColumn(name = "tg_user_id", referencedColumnName = "chat_id")
    private TelegramUser owner;

    public Cryptocurrency(String name, double expectedRate, int expectedAction) {
        this.name = name;
        this.expectedRate = expectedRate;
        this.expectedAction = expectedAction;
    }

    public Cryptocurrency() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExpectedRate() {
        return expectedRate;
    }

    public void setExpectedRate(double expectedRate) {
        this.expectedRate = expectedRate;
    }

    public TelegramUser getOwner() {
        return owner;
    }

    public void setOwner(TelegramUser owner) {
        this.owner = owner;
    }

    public int getExpectedAction() {
        return expectedAction;
    }

    public void setExpectedAction(int expectedAction) {
        this.expectedAction = expectedAction;
    }
}
