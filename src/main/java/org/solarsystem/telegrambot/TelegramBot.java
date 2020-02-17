package org.solarsystem.telegrambot;

import org.solarsystem.web.service.CalcDistance;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelegramBot extends TelegramLongPollingBot {

    public static List<String> listPlanet = new ArrayList<>();


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

                        if (botsServiceImpl.getPlanetFirst() != null) {
                            sendMsg(update.getMessage().getChatId().toString(), botsServiceImpl.getInfo(botsServiceImpl.getPlanetFirst()));
                        } else {
                            try {
                                execute(CalendarAddButtons.sendInlineKeyBoardMessage(update
                                                .getMessage().getChatId()
                                        , CalendarAddButtons.setInlineKeyboardPlanet(update.getMessage().getChatId()))); // Call method to send the message
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
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
                        //listPlanet.clear();
                        if (botsServiceImpl.getPlanetFirst() != null
                                && botsServiceImpl.getPlanetFirst() != null
                                && botsServiceImpl.isPlanet(botsServiceImpl.getPlanetFirst())
                                && botsServiceImpl.isPlanet(botsServiceImpl.getPlanetSecond())
                                && botsServiceImpl.corectDate(botsServiceImpl.getDate())) {
                            double distance = CalcDistance.getDistance(botsServiceImpl.getPlanetFirst(), botsServiceImpl.getPlanetSecond(), botsServiceImpl.getDate());

                            sendMsg(update.getMessage().getChatId().toString(), "Distance between " + botsServiceImpl.getPlanetFirst() + " and " + botsServiceImpl.getPlanetSecond() + " is " + distance + " AU");

                        } else if (botsServiceImpl.getPlanetFirst() != null) {

                            sendMsg(update.getMessage().getChatId().toString(), "Incorrect planet or date format. Input \"/aboutdistance\" to show available planet and date format. \n");


                            // sendMsg(update.getMessage().getChatId().toString(), "Incorrect planet or date format. Input \"/aboutdistance\" to show available planet and date format. ");
                        } else {
                            try {

                                execute(CalendarAddButtons.sendInlineKeyBoardMessage(update
                                                .getMessage().getChatId()
                                        , CalendarAddButtons.setKeyboardPlanetDistanceFirst(update.getMessage().getChatId()))); // Call method to send the message
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                    case ("aboutdistance"):

                        sendMsg(update.getMessage().getChatId().toString(), "Available planet are :"
                                + "\n" + botsServiceImpl.getAllSpaceBodyNames() + "\n" + "Date format is yyyy-MM-dd.\n"
                                + "Correct input <distance mars venus 2020-05-23>");
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


            if (call_data.startsWith("*Planet_name_is:*")) {
                BotsServiceImpl botsService = new BotsServiceImpl("/info " + call_data.substring("*Planet_name_is:*".length()));


                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(botsService.getInfo(botsService.getPlanetFirst()));

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (call_data.startsWith("*Planet_firts_name_is:*")) {

                BotsServiceImpl botsService = new BotsServiceImpl("/distance " + call_data.substring("*Planet_firts_name_is:*".length()));
                //botsService.getPlanetToDistance().add(call_data.substring("*Planet_firts_name_is:*".length()));
                User user = update.getCallbackQuery().getFrom();

                Integer userId = user.getId();
                listPlanet.add(call_data.substring("*Planet_firts_name_is:*".length()) + "userId" + userId);
            } else if (call_data.startsWith("*button_number_calendar*")) {
                LocalDate localDate = LocalDate.parse(call_data.substring("*button_number_calendar*".length()), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (listPlanet.stream().filter(e -> e.endsWith("userId" + update.getCallbackQuery()
                        .getFrom().getId())).collect(Collectors.toList()).size()>1){

                    List<String> collect = listPlanet.stream().filter(e -> e.endsWith("userId" + update.getCallbackQuery()
                            .getFrom().getId())).collect(Collectors.toList());
                    String planetDestination = collect.get(collect.size() - 1).substring(0,collect.get(collect.size() - 1).length()-15);

                    String planetStart = collect.get(collect.size() - 2).substring(0,collect.get(collect.size() - 2).length()-15);
                    double distance = new BotsServiceImpl().getDistance(planetStart, planetDestination, localDate);

                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Distance between " + planetStart + " and " + planetDestination + " in " + localDate.toString() + " is " + distance + " AU.");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        execute(new SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setText("Choose one more planet \"/distance\"")); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }






            } else if (call_data.startsWith("*Choose_date_is:*")) {

                try {


                    new_message.setReplyMarkup(CalendarAddButtons.setInlineKeyboad(chat_id));
                    InlineKeyboardMarkup replyMarkup = new_message.getReplyMarkup();

                    execute(new_message);

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

            execute(sendMessage);
            // Call method to send the message
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
