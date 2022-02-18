import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SampleBot extends TelegramLongPollingBot {

    private final HashSet<String> someUsedWords = new HashSet<>();

    @Override
    public String getBotUsername() {
        return "semenov228bot";
    }

    @Override
    public String getBotToken() {
        return "5229335629:AAG_C-Deq1SRT5EYNL_Z1HBsgytt0uJ4h-o";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var text = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            someUsedWords.add(text);

            var messageQuery = new SendMessage();
            messageQuery.setChatId(String.valueOf(chatId));
            messageQuery.setText(getRandomSetElement(someUsedWords));

            try {
                execute(messageQuery);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private static <E> E getRandomSetElement(Set<E> set) {
        return set
                .stream()
                .skip(new Random().nextInt(set.size()))
                .findFirst()
                .orElse(null);
    }
}
