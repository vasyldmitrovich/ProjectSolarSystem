package org.solarsystem.telegrambot;

import org.solarsystem.web.service.CalcDistance;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    public static void main(String args[]) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        String availableCommands = BotsService.getAvailableCommands();
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText();
            BotsService botsService = new BotsService(message);

            switch (botsService.getCommand()) {
                case "help":
                    sendMsg(update.getMessage().getChatId().toString(), availableCommands);
                    break;
                case "distance":
                    if (botsService.getPlanetFirst() != null && botsService.isPlanet(botsService.getPlanetFirst()) && botsService.isPlanet(botsService.getPlanetSecond())) {
                        double distance = CalcDistance.getDistance(botsService.getPlanetFirst(), botsService.getPlanetSecond(), botsService.getDate());

                        sendMsg(update.getMessage().getChatId().toString(), String.valueOf(distance));
                        break;
                    } else {

                        sendMsg(update.getMessage().getChatId().toString(), "incorrect planet, check planet name\n" +
                                "available planet are: \"Mercury\",\"Venus\",\"Earth\"\"Mars\",\"Jupiter\",\"Saturn\",\"Uranus\",\"Neptune\",\"Pluto\":");
                    }

                    break;

                default:
                    sendMsg(update.getMessage().getChatId().toString(), "incorrect command\n"+availableCommands);
            }


        }
    }

    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);


        try {

            execute(sendMessage); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public String getBotUsername() {

        return "PlanetaryFactBot";
    }


    public String getBotToken() {

        return "1093856664:AAFcU9c_fp043gXMUsoDrkjsoxLwHp0Gafg";
    }
}
