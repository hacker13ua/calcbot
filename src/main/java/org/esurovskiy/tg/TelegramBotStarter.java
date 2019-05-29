package org.esurovskiy.tg;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TelegramBotStarter {
    public static void main(String[] args)
            throws TelegramApiRequestException {
        ApiContextInitializer.init();
        final TelegramBotsApi api = new TelegramBotsApi();
        api.registerBot(new SuperEncryptorBot());
    }
}
