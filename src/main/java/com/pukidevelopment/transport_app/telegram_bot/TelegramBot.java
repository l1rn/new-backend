package com.pukidevelopment.transport_app.telegram_bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    public TelegramBot() {
        logger.info("TelegramBot initialized");
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Received update: {}", update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (messageText.equals("/start")) {
                logger.info("Received /start command from chatId: {}", chatId);
                sendMessage(chatId, "Привет! Нажмите кнопку, чтобы поделиться номером телефона.", true);
            }
        }
    }

    private void sendMessage(long chatId, String text, boolean withContactButton) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        if (withContactButton) {
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setOneTimeKeyboard(true);
            keyboardMarkup.setResizeKeyboard(true);

            KeyboardButton button = new KeyboardButton("Поделиться номером");
            button.setRequestContact(true);

            KeyboardRow row = new KeyboardRow();
            row.add(button);

            keyboardMarkup.setKeyboard(List.of(row));
            message.setReplyMarkup(keyboardMarkup);
        }

        try {
            logger.info("Sending message to chatId: {} with text: {}", chatId, text);
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error sending message: ", e);
        }
    }
}