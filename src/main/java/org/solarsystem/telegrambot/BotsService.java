package org.solarsystem.telegrambot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class BotsService {
    private String command;
    private String planetFirst;
    private String planetSecond;
    private String date;


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPlanetFirst() {
        return planetFirst;
    }

    public void setPlanetFirst(String planetFirst) {
        this.planetFirst = planetFirst;
    }

    public String getPlanetSecond() {
        return planetSecond;
    }

    public void setPlanetSecond(String planetSecond) {
        this.planetSecond = planetSecond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public BotsService(String str) {
        Deque<String> list = new LinkedList<>();
        list = parseCommands(str);
        while (!list.isEmpty()) {
            command = list.pollFirst();
            planetFirst = list.pollFirst();
            planetSecond = list.pollFirst();
            date = list.pollFirst();

            return;
        }
    }


    public static String getAvailableCommands() {
        // TODO get available commands
        List<String> commands = new ArrayList<>();
        commands.add("\"/distance\" \"planetName\" \"planetName\" \"yyyy-MM-dd\" will calculate distance between planets");
        commands.add("\"/time\" will calculate estimate the travel time for a journey ");
        commands.add("\"/info\" get info about planet");
        commands.add("\"/image\" get planet image");
        return "Available commands are: \n" + commands.stream().collect(Collectors.joining("\n"));
    }

    public Deque<String> parseCommands(String commands) {
        Deque<String> list = new LinkedList<>();
        String[] s = commands.trim().toLowerCase().split("[ ./,_?!;:]");
        for (String str : s) {
            if (!str.isEmpty()) {
                list.add(str);
            }
        }

        return list;
    }



    public static synchronized void setButtons(SendMessage sendMessage) {
        // create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // create list of our row
        List<KeyboardRow> keyboard = new ArrayList<>();

        //create row
        KeyboardRow firstRow = new KeyboardRow();

        // create button
        KeyboardButton help = new KeyboardButton("Help");

        KeyboardButton availableCommand = new KeyboardButton("Available Command");

        //add buttons to keyboardrow
        firstRow.add(help);
        //firstRow.add(availableCommand);

        // add ours row to keyboard
        keyboard.add(firstRow);

        // adding our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


    public boolean isPlanet(String str) {
        switch (str) {
            case "mercury":
                return true;

            case "venus":
                return true;

            case "earth":
                return true;

            case "mars":
                return true;

            case "jupiter":
                return true;

            case "saturn":
                return true;

            case "uranus":
                return true;

            case "neptune":
                return true;

            case "pluto":
                return true;

            default:
                return false;
        }


    }

    @Override
    public String toString() {
        return "BotsService{" +
                "command='" + command + '\'' +
                ", planetFirst='" + planetFirst + '\'' +
                ", planetSecond='" + planetSecond + '\'' +
                ", date='" + date + '\'' +
                '}';

    }

}
