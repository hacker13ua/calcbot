package org.esurovskiy.tg;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SuperEncryptorBot extends TelegramLongPollingBot {
    private Encryptor encryptor = new SimpleEncryptor();

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasInlineQuery()) {
            String query = update.getInlineQuery().getQuery();
            String enctypted = encryptor.encrypt(query);

            AnswerInlineQuery answer =
                    new AnswerInlineQuery();
            answer.setInlineQueryId(
                    update.getInlineQuery().getId());
            InlineQueryResultArticle inlineQueryResult =
                    new InlineQueryResultArticle();
            inlineQueryResult
                    .setId("1")
                    .setTitle(enctypted)
                    .setInputMessageContent(
                            new InputTextMessageContent()
                                    .setMessageText(enctypted));
            answer.setResults(inlineQueryResult);
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            System.out.println(query);
        } else if (update.hasMessage()) {
            String text = update.getMessage().getText();
            String enctypted = encryptor.encrypt(text);
            Long chatId = update.getMessage().getChatId();
            SendMessage message =
                    new SendMessage(chatId, enctypted);
            try {
                execute(message);
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
