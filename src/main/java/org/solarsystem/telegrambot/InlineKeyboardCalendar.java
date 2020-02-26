package org.solarsystem.telegrambot;

import org.solarsystem.web.service.PlanetInfoImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InlineKeyboardCalendar {

    public static LocalDate localDate = LocalDate.now();
    private InlineKeyboardMarkup inlineKeyboardMarkup;


    public InlineKeyboardCalendar() {

    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }



    public SendMessage sendMessage(long chatId) {
        InlineKeyboardBuilder inlineKeyboardBuilder = InlineKeyboardBuilder.create(chatId).setText("Select a date");

        inlineKeyboardBuilder
                .row()
                .button("<", "*yearreduce*")
                .button(String.valueOf(localDate.getYear()), "*do not answer*")
                .button(">", "*yearadd*")
                .endRow()
                .row()
                .button("<", "*monthreduce*")
                .button(localDate.getMonth().toString(), "*do not answer*")
                .button(">", "*monthadd*")
                .endRow();
        String yearAndMonth = localDate.getYear() + "-" + (localDate.getMonthValue() < 10 ? "0" + localDate.getMonthValue() : localDate.getMonthValue()) + "-";

        for (int countDay = 1, countRow = 0; countRow < 4; countRow++) {
            inlineKeyboardBuilder.row();
            for (; countDay <= 8; countDay++) {
                inlineKeyboardBuilder
                        .button("0" + countDay, "*button_number_calendar*" + yearAndMonth + "0" + countDay);

            }
            inlineKeyboardBuilder
                    .endRow()
                    .row();
            for (; countDay == 9; countDay++) {
                inlineKeyboardBuilder

                        .button("0" + countDay, "*button_number_calendar*" + yearAndMonth + "0" + countDay);
            }
            for (; countDay > 9 && countDay <= 16; countDay++) {
                inlineKeyboardBuilder
                        .button("" + countDay, "*button_number_calendar*" + yearAndMonth + countDay);

            }
            inlineKeyboardBuilder
                    .endRow()
                    .row();
            for (; countDay > 16 && countDay <= 24; countDay++) {
                inlineKeyboardBuilder

                        .button("" + countDay, "*button_number_calendar*" + yearAndMonth + countDay);

            }
            inlineKeyboardBuilder
                    .endRow()
                    .row();
            for (; countDay > 24 && countDay <= localDate.lengthOfMonth(); countDay++) {
                inlineKeyboardBuilder

                        .button("" + countDay, "*button_number_calendar*" + yearAndMonth + countDay);

            }
            inlineKeyboardBuilder.endRow();


        }
        SendMessage build = inlineKeyboardBuilder.build();
        inlineKeyboardMarkup = inlineKeyboardBuilder.getInlineKeyboardMarkup();
        return build;


    }



}