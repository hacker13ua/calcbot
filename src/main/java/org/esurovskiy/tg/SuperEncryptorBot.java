package org.esurovskiy.tg;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

public class SuperEncryptorBot extends TelegramLongPollingBot {
    private Encryptor encryptor = new SimpleEncryptor();
    private Encryptor encryptor2 = new SimpleEncryptorDown();

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasInlineQuery()) {
            String query = update.getInlineQuery().getQuery();

            AnswerInlineQuery answer = new AnswerInlineQuery();
            answer.setInlineQueryId(
                    update.getInlineQuery().getId());
            InlineQueryResultArticle result1 =
                    getEncryptedResult(query, encryptor);
            InlineQueryResultArticle result2 =
                    getEncryptedResult(query, encryptor2);
            answer.setResults(result1, result2);
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            System.out.println(query);
        }
    }

    private InlineQueryResultArticle getEncryptedResult(
            String text, Encryptor encryptor) {
        String enctypted = encryptor.encrypt(text);
        Random rnd = new Random();
        InlineQueryResultArticle inlineQueryResult =
                new InlineQueryResultArticle();
        inlineQueryResult
                .setId(String.valueOf(rnd.nextInt()))
                .setTitle("Encrypted text: " + enctypted)
                .setInputMessageContent(
                        new InputTextMessageContent()
                                .setMessageText(enctypted));
        return inlineQueryResult;
    }

    public String getBotUsername() {
        return "calc_example_bot";
    }

    public String getBotToken() {
        return "835939536:AAHUhLHM5IOSA_hbkNnsRyzEFoIloSgu4dM";
    }
}
