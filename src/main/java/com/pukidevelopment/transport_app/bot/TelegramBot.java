package com.pukidevelopment.transport_app.bot;


import com.pukidevelopment.transport_app.model.User;
import com.pukidevelopment.transport_app.repo.UserRepo;
import com.pukidevelopment.transport_app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;


@Component
@AllArgsConstructor
public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;

    public TelegramBot(String botToken){
        telegramClient = new OkHttpTelegramClient("8063211434:AAGFNMwI-kStDKucYlLz4qm4xijnY7NYuu4");
    }

    @Override
    public void consume(Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String telegramId = update.getMessage().getChat().getUserName();

            User user = User.builder()
                    .chatId(chat_id)
                    .telegramId(telegramId)
                    .phoneNumber(null)
                    .build();


            SendMessage message = SendMessage
                    .builder()
                    .chatId(chat_id)
                    .text(message_text)
                    .build();
            try {
                telegramClient.execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
}
