import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SampleBot extends TelegramLongPollingBot {

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
            var messageQuery = new SendMessage();
            messageQuery.setChatId(String.valueOf(chatId));
            messageQuery.setText(text);
            try {
                execute(messageQuery);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
