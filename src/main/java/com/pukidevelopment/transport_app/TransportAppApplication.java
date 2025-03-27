package com.pukidevelopment.transport_app;

import com.pukidevelopment.transport_app.bot.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@SpringBootApplication
public class TransportAppApplication {
	public static void main(String[] args) {
		String botToken = "8063211434:AAGFNMwI-kStDKucYlLz4qm4xijnY7NYuu4";
		try(TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()){
			botsApplication.registerBot(botToken, new TelegramBot(botToken));
			System.out.println("MUSSTAAAARD!");
			Thread.currentThread().join();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
//		SpringApplication.run(TransportAppApplication.class);
	}
}