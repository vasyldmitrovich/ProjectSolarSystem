package org.solarsystem.telegrambot;


import org.solarsystem.web.service.CalcDistance;
import org.solarsystem.web.service.NasaJson;
import org.solarsystem.web.service.PlanetInfoImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



public class BotsServiceImpl implements BotService {
    private String command;
    private String planetFirst;
    private String planetSecond;
    private String date;
    private List<String> planetToDistance;


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


    public BotsServiceImpl(String str) {
        planetToDistance = new ArrayList<>();
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

    public BotsServiceImpl() {
    }

    public List<String> getPlanetToDistance() {
        return planetToDistance;
    }

    public void setPlanetToDistance(List<String> planetToDistance) {
        this.planetToDistance = planetToDistance;
    }

    // return all commands witch available in bot
    @Override
    public String getAvailableCommands() {

        List<String> commands = new ArrayList<>();
        commands.add("\"/allplanets\""+" -return all available planets for getting description");
        commands.add("\"/allbodies\""+" -return all available space body for calculate distance between them");
        commands.add("\"/distance\""+" - calculate distance between planet");
        //commands.add("\"/time\" ");
        commands.add("\"/info\" "+" -return description about chosen planet");
        return String.join("\n", commands);
    }

    //split  string from chat to word
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

    //add button "Help" to bottom of the screen
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


        //add buttons to keyboardrow
        firstRow.add(help);
        //firstRow.add(availableCommand);

        // add ours row to keyboard
        keyboard.add(firstRow);

        // adding our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


    public boolean isPlanet(String str) {
        List<String> list = new NasaJson().getAvailablePlanet();
        return list.contains(str);

    }

    @Override
    public String toString() {
        return "BotsServiceImpl{" +
                "command='" + command + '\'' +
                ", planetFirst='" + planetFirst + '\'' +
                ", planetSecond='" + planetSecond + '\'' +
                ", date='" + date + '\'' +
                '}';

    }

    @Override
    public List<String> getAllSpaceBodyNames() {
        return new NasaJson().getAvailablePlanet();

    }

    @Override
    public List<String> getAllPlanetName() {
        return null;
    }

    @Override
    public double getDistance(String originPlanet, String destinationPlanetUuid, LocalDate date) {
//        String dateStr = date.getYear() + "-" + ((date.getMonthValue() < 10) ? "0" + date.getMonthValue() : date.getMonthValue()) + "-"
//                + ((date.getDayOfMonth() < 10) ? "0" + date.getDayOfMonth() : date.getDayOfMonth());
        return new NasaJson().calculateDistance(originPlanet, destinationPlanetUuid, date);


    }

    @Override
    public double getTime(String originPlanet, String destinationPlanetUuid, LocalDate date, double speed) {
        return 0;
    }

    @Override
    public String getInfo(String planetName) {
        return new PlanetInfoImpl().getShortDescription(planetName);

    }


    //validate date
    public boolean isCorectDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate parse = LocalDate.parse(date, formatter);

            if (Integer.parseInt(date.substring(8)) > parse.getDayOfMonth()) {
                return false;
            }

            return true;
        } catch (DateTimeParseException excep) {
            return false;
        }

    }


}
