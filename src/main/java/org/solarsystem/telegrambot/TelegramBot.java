package org.solarsystem.telegrambot;

import org.solarsystem.web.service.CalcDistance;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.stream.Collectors;

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
        String availableCommands = BotsServiceImpl.getAvailableCommands();
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String message = update.getMessage().getText();
                BotsServiceImpl botsServiceImpl = new BotsServiceImpl(message);
                String planetList = botsServiceImpl.getAllSpaceBodyNames().stream().collect(Collectors.joining(", "));
                switch (botsServiceImpl.getCommand()) {
                    case "help":
                        sendMsg(update.getMessage().getChatId().toString(), availableCommands);
                        break;
                    case "info":
                        //sendMsg(update.getMessage().getChatId().toString(), "Choose planet or satellite: ");
                        try {

                            execute(CalendarAddButtons.sendInlineKeyBoardMessage(update
                                            .getMessage().getChatId()
                                    , CalendarAddButtons.setInlineKeyboardPlanet(update.getMessage().getChatId()))); // Call method to send the message
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "allplanets":
                        sendMsg(update.getMessage().getChatId().toString(), planetList);
                        break;
                    case "calendar":
                        try {

                            execute(CalendarAddButtons.sendInlineKeyBoardMessage(update
                                            .getMessage().getChatId()
                                    , CalendarAddButtons.setInlineKeyboad(update.getMessage().getChatId()))); // Call method to send the message
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "distance":
                        if (botsServiceImpl.getPlanetFirst() != null && botsServiceImpl.isPlanet(botsServiceImpl.getPlanetFirst()) && botsServiceImpl.isPlanet(botsServiceImpl.getPlanetSecond())) {
                            double distance = CalcDistance.getDistance(botsServiceImpl.getPlanetFirst(), botsServiceImpl.getPlanetSecond(), botsServiceImpl.getDate());

                            sendMsg(update.getMessage().getChatId().toString(), String.valueOf(distance));
                            break;
                        } else {

                            sendMsg(update.getMessage().getChatId().toString(), "incorrect planet, check planet name\n" +
                                    "available planet are: \"Mercury\",\"Venus\",\"Earth\"\"Mars\",\"Jupiter\",\"Saturn\",\"Uranus\",\"Neptune\",\"Pluto\":");
                        }

                        break;

                    default:
                        sendMsg(update.getMessage().getChatId().toString(), "<" + message + ">" + " :command not found.\n Input \"/help\" to show available commands.");
                }


            }
        } else if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();

            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            int message_id = update.getCallbackQuery().getMessage().getMessageId();
            String inline_message_id = update.getCallbackQuery().getInlineMessageId();
            EditMessageReplyMarkup new_message = new EditMessageReplyMarkup()
                    .setChatId(chat_id).setMessageId(message_id)
                    .setInlineMessageId(inline_message_id);
            if (call_data.startsWith("*button_number_calendar*")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(call_data.substring("*button_number_calendar*".length()));

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                switch (call_data) {
                    case "*monthreduce*":
                        CalendarAddButtons.localDate = CalendarAddButtons.localDate.minusMonths(1);
                        break;
                    case "*monthadd*":
                        CalendarAddButtons.localDate = CalendarAddButtons.localDate.plusMonths(1);
                        break;
                    case "*yearreduce*":
                        CalendarAddButtons.localDate = CalendarAddButtons.localDate.minusYears(1);
                        break;
                    case "*yearadd*":
                        CalendarAddButtons.localDate = CalendarAddButtons.localDate.plusYears(1);
                        break;
                    case "*do not answer*":
                        break;
                }

                try {


                    new_message.setReplyMarkup(CalendarAddButtons.setInlineKeyboad(chat_id));
                    InlineKeyboardMarkup replyMarkup = new_message.getReplyMarkup();

                    execute(new_message);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        BotsServiceImpl.setButtons(sendMessage);


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
