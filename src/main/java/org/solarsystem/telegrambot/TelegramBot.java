package org.solarsystem.telegrambot;

import org.apache.log4j.Logger;
import org.solarsystem.web.service.NasaJson;
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
    public static final Logger log = Logger.getLogger(TelegramBot.class);

    public static void main(String args[]) {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramBot());
            log.info("Telegram bot register on server ");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


    public void onUpdateReceived(Update update) {

        InlineKeyboardCalendar inlineKeyboardCalendar = new InlineKeyboardCalendar();
        if (update.hasMessage()) {


            if (update.getMessage().hasText()) {
                String message = update.getMessage().getText();
                BotsServiceImpl botsServiceImpl = new BotsServiceImpl(message);
                String bodiestList = botsServiceImpl.getAllSpaceBodyNames().stream().collect(Collectors.joining(", "));

                switch (botsServiceImpl.getCommand()) {
                    case "help":
                        sendMsg(update.getMessage().getChatId().toString(), botsServiceImpl.getAvailableCommands());

                        break;
                    case "info":

                        if (botsServiceImpl.getPlanetFirst() != null) {
                            sendMsg(update.getMessage().getChatId().toString(), botsServiceImpl.getInfo(botsServiceImpl.getPlanetFirst()));
                        } else {
                            try {
                                execute(new InlineKeyboardToInfo().sendMessage(update.getMessage().getChatId()));

                            } catch (TelegramApiException e) {
                                log.error("Show form with planet info " + e.getMessage());
                            }
                        }

                        break;
                    case "allplanets":
                        sendMsg(update.getMessage().getChatId().toString(), String.join(", ", botsServiceImpl.getAllPlanetName()));

                        break;
                    case "allbodies":
                        sendMsg(update.getMessage().getChatId().toString(), bodiestList);

                        break;
                    case "calendar":
                        try {

                            execute(new InlineKeyboardCalendar().sendMessage(update.getMessage().getChatId()));

                        } catch (TelegramApiException e) {
                            log.error("Show calendar" + e.getMessage());
                        }
                        break;
                    case "distance":

                        if (botsServiceImpl.getPlanetFirst() != null
                                && botsServiceImpl.getPlanetSecond() != null
                                && botsServiceImpl.getDate() != null
                                && botsServiceImpl.isPlanet(botsServiceImpl.getPlanetFirst())
                                && botsServiceImpl.isPlanet(botsServiceImpl.getPlanetSecond())
                                && botsServiceImpl.isCorectDate(botsServiceImpl.getDate())) {
                            LocalDate localDate = LocalDate.parse(botsServiceImpl.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            double distance = new NasaJson().
                                    calculateDistance(botsServiceImpl.getPlanetFirst(), botsServiceImpl.getPlanetSecond(), localDate);
                            sendMsg(update.getMessage().getChatId().toString(), "Distance between " + botsServiceImpl.getPlanetFirst() + " and " + botsServiceImpl.getPlanetSecond() + " is " + distance + " AU");

                        } else if (botsServiceImpl.getPlanetFirst() != null) {

                            sendMsg(update.getMessage().getChatId().toString(), "Incorrect planet or date format. Input \"/aboutdistance\" to show available planet and date format. \n");

                        } else {
                            try {
                                execute(new InlineKeyboardToDistance().sendMessage(update.getMessage().getChatId()));
                            } catch (TelegramApiException e) {
                                log.error("Show form with planet distance " + e.getMessage());
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


                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText(botsService.getInfo(botsService.getPlanetFirst()));

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    log.error("Show info about planet " + e.getMessage());
                }
            } else if (call_data.startsWith("*Planet_firts_name_is:*")) {

                BotsServiceImpl botsService = new BotsServiceImpl("/distance " + call_data.substring("*Planet_firts_name_is:*".length()));
                User user = update.getCallbackQuery().getFrom();

                Integer userId = user.getId();

                listPlanet.add(call_data.substring("*Planet_firts_name_is:*".length()) + "userId" + userId);
            } else if (call_data.startsWith("*button_number_calendar*")) {
                LocalDate localDate = LocalDate.parse(call_data.substring("*button_number_calendar*".length()), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (listPlanet.stream().filter(e -> e.endsWith("userId" + update.getCallbackQuery()
                        .getFrom().getId())).collect(Collectors.toList()).size() > 3) {
                    List<String> collectToDalete = listPlanet.stream().filter(e -> e.endsWith("userId" + update.getCallbackQuery()
                            .getFrom().getId())).collect(Collectors.toList());
                    collectToDalete.size();
                    listPlanet.removeAll(collectToDalete.subList(0, collectToDalete.size() - 2));
                }
                if (listPlanet.stream().filter(e -> e.endsWith("userId" + update.getCallbackQuery()
                        .getFrom().getId())).collect(Collectors.toList()).size() > 1) {
                    List<String> collect = listPlanet.stream().filter(e -> e.endsWith("userId" + update.getCallbackQuery()
                            .getFrom().getId())).collect(Collectors.toList());

                    String planetDestination = collect.get(collect.size() - 1)
                            .substring(0, collect.get(collect.size() - 1).length() - "userId".length() - String.valueOf(update.getCallbackQuery().getFrom().getId()).length());

                    String planetStart = collect.get(collect.size() - 2)
                            .substring(0, collect.get(collect.size() - 2).length() - "userId".length() - String.valueOf(update.getCallbackQuery().getFrom().getId()).length());


                    double distance = new BotsServiceImpl().getDistance(planetStart, planetDestination, localDate);

                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Distance between " + planetStart + " and " + planetDestination + " in " + localDate.toString() + " is " + distance + " AU");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        //log.error("Show distance between planets "+userTel +" " +e.getMessage());
                    }
                } else {
                    try {
                        execute(new SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setText("Choose one more planet \"/distance\"")); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        log.error("Show form with planet distance. Choose one more planet " + e.getMessage());
                    }
                }


            } else if (call_data.startsWith("*Choose_date_is:*")) {

                try {


                    inlineKeyboardCalendar.sendMessage(chat_id);

                    new_message.setReplyMarkup(inlineKeyboardCalendar.getInlineKeyboardMarkup());
                    InlineKeyboardMarkup replyMarkup = new_message.getReplyMarkup();
                    execute(new_message);

                } catch (TelegramApiException e) {
                    log.error("Change form with planet to calendar " + e.getMessage());
                }
            } else {

                switch (call_data) {



                    case "*monthreduce*":
                        inlineKeyboardCalendar.setLocalDate(inlineKeyboardCalendar.getLocalDate().minusMonths(1));
                        break;
                    case "*monthadd*":
                        inlineKeyboardCalendar.setLocalDate(inlineKeyboardCalendar.getLocalDate().plusMonths(1));
                        break;
                    case "*yearreduce*":
                        inlineKeyboardCalendar.setLocalDate(inlineKeyboardCalendar.getLocalDate().minusYears(1));
                        break;
                    case "*yearadd*":
                        inlineKeyboardCalendar.setLocalDate(inlineKeyboardCalendar.getLocalDate().plusYears(1));
                        break;
                    case "*do not answer*":
                        break;
                }


                try {


                    inlineKeyboardCalendar.sendMessage(chat_id);

                    new_message.setReplyMarkup(inlineKeyboardCalendar.getInlineKeyboardMarkup());

                    InlineKeyboardMarkup replyMarkup = new_message.getReplyMarkup();

                    execute(new_message);

                } catch (TelegramApiException e) {
                    log.error("Change calendar " + e.getMessage());
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
            log.error("Show message " + e.getMessage());
        }
    }


    public String getBotUsername() {

        return "PlanetaryFactBot";
    }


    public String getBotToken() {

        return "1093856664:AAFcU9c_fp043gXMUsoDrkjsoxLwHp0Gafg";
    }
}
