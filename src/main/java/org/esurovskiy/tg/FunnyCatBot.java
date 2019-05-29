package org.esurovskiy.tg;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class FunnyCatBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(final Update update) {
        SendPhoto sendPhoto = new SendPhoto();
        if (update.getMessage() != null) {
            Long chatId = update.getMessage().getChatId();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(new File(
                    "/home/esurovskiy/cat.jpeg"));

            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "calc_example_bot";
    }

    public String getBotToken() {
        return "835939536:AAHUhLHM5IOSA_hbkNnsRyzEFoIloSgu4dM";
    }
}
