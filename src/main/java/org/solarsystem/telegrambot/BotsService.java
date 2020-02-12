package org.solarsystem.telegrambot;


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
        commands.add("\"/distance\" will calculate distance between planets");
        commands.add("\"/time\" will calculate estimate the travel time for a journey ");
        commands.add("\"/info\" get info about planet");
        commands.add("\"/image\" get planet image");
        return commands.stream().collect(Collectors.joining("\n"));
    }

    public Deque<String> parseCommands(String commands) {
        Deque<String> list = new LinkedList<>();
        String[] s = commands.trim().toLowerCase().split("[ ./,_]");
        for (String str : s) {
            if (!str.isEmpty()) {
                list.add(str);
            }
        }

        return list;
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
