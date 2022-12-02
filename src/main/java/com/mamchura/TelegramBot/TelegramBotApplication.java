package com.mamchura.TelegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:bot.properties")
public class TelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);
	}

}
