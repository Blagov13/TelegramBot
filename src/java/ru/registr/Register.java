package java.ru.registr;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Register extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Register());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("register")) {
                    String userId = registerUser(message.getChat().getId());
                    if (userId != null) {
                        sendMessage(message.getChat().getId(), "User registered successfully");
                    } else {
                        sendMessage(message.getChat().getId(), "Registration failed");
                    }
                } else if (text.equals("login")) {
                    boolean isLoggedIn = loginUser(message.getChat().getId());
                    if (isLoggedIn) {
                        sendMessage(message.getChat().getId(), "Login successful");
                    } else {
                        sendMessage(message.getChat().getId(), "Login failed");
                    }
                }
            }
        }
    }
}
