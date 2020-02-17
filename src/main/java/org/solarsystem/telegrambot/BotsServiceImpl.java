package org.solarsystem.telegrambot;


import org.solarsystem.web.service.CalcDistance;
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
        planetToDistance=new ArrayList<>();
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

    public static String getAvailableCommands() {
        // TODO get available commands
        List<String> commands = new ArrayList<>();
        commands.add("\"/allplanets\"");
        commands.add("\"/distance\"");
        //commands.add("\"/time\" ");
        commands.add("\"/info\" ");
        //commands.add("\"/image\"");
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
        return "BotsServiceImpl{" +
                "command='" + command + '\'' +
                ", planetFirst='" + planetFirst + '\'' +
                ", planetSecond='" + planetSecond + '\'' +
                ", date='" + date + '\'' +
                '}';

    }

    @Override
    public List<String> getAllSpaceBodyNames() {


        List<String> planetsName = new ArrayList<>();
        planetsName.add("Mercury");
        planetsName.add("Venus");
        planetsName.add("Earth");
        planetsName.add("Mars");
        planetsName.add("Jupiter");
        planetsName.add("Saturn");
        planetsName.add("Uranus");
        planetsName.add("Neptune");
        planetsName.add("Pluto");
        planetsName.add("Pluto1");
        planetsName.add("Pluto2");
        planetsName.add("Pluto3");
        planetsName.add("Pluto4");
        planetsName.add("Pluto5");
        planetsName.add("Pluto6");
        planetsName.add("Pluto7");
        planetsName.add("Pluto8");
        planetsName.add("Pluto9");
        planetsName.add("Pluto10");
        planetsName.add("Pluto11");
        planetsName.add("Pluto12");
        planetsName.add("Pluto13");
        planetsName.add("Pluto14");
        planetsName.add("Pluto15");
        planetsName.add("Pluto16");
        planetsName.add("Pluto17");
        planetsName.add("Pluto18");
        planetsName.add("Pluto19");
        planetsName.add("Pluto20");
        planetsName.add("Pluto21");
        planetsName.add("Pluto22");


        return planetsName;
    }

    @Override
    public double getDistance(String originPlanet, String destinationPlanetUuid, LocalDate date) {
        String dateStr = date.getYear()+"-"+((date.getMonthValue()<10)? "0"+date.getMonthValue():date.getMonthValue())+"-"
                +((date.getDayOfMonth()<10)? "0"+date.getDayOfMonth():date.getDayOfMonth());
       return CalcDistance.getDistance(originPlanet,destinationPlanetUuid,dateStr);

    }

    @Override
    public double getTime(String originPlanet, String destinationPlanetUuid, LocalDate date, double speed) {
        return 0;
    }

    @Override
    public String getInfo(String planetName) {

        return "Short description about planet "+planetFirst+"\n"+"Tra ta ta";
    }



    public   boolean corectDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate parse = LocalDate.parse(date, formatter);

            if (Integer.parseInt(date.substring(8))>parse.getDayOfMonth()){
                return false;
            }

            return true;
        }catch(DateTimeParseException excep){
            return false;
        }

    }
}
