package org.solarsystem.telegrambot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarAddButtons {
    public static LocalDate localDate = LocalDate.now();


    public static InlineKeyboardMarkup setInlineKeyboad(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //adding buttons month and change month
        InlineKeyboardButton previousMonth = new InlineKeyboardButton();
        InlineKeyboardButton dateMonth = new InlineKeyboardButton();
        InlineKeyboardButton nextMonth = new InlineKeyboardButton();

        previousMonth.setText("<");
        previousMonth.setCallbackData("*monthreduce*");

        dateMonth.setText(localDate.getMonth().toString());
        dateMonth.setCallbackData("*do not answer*");

        nextMonth.setText(">");
        nextMonth.setCallbackData("*monthadd*");

        //adding buttons year and change year
        InlineKeyboardButton previousYear = new InlineKeyboardButton();
        InlineKeyboardButton dateYear = new InlineKeyboardButton();
        InlineKeyboardButton nextYear = new InlineKeyboardButton();

        previousYear.setText("<");
        previousYear.setCallbackData("*yearreduce*");

        dateYear.setText(localDate.getYear() + "");
        dateYear.setCallbackData("*do not answer*");

        nextYear.setText(">");
        nextYear.setCallbackData("*yearadd*");


        List<InlineKeyboardButton> keyboardButtonsRow0 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();

        //add row with year
        keyboardButtonsRow0.add(previousYear);
        keyboardButtonsRow0.add(dateYear);
        keyboardButtonsRow0.add(nextYear);

        //add row with month
        keyboardButtonsRow1.add(previousMonth);

        keyboardButtonsRow1.add(dateMonth);
        keyboardButtonsRow1.add(nextMonth);

        String yearAndMonth = keyboardButtonsRow0.get(1).getText() + "-" + (localDate.getMonthValue() < 10 ? "0" + localDate.getMonthValue() : localDate.getMonthValue()) + "-";

        for (int i = 1; i <= localDate.lengthOfMonth(); i++) {

            if (i <= 8) {
                keyboardButtonsRow2.add(new InlineKeyboardButton("0" + i).setCallbackData("*button_number_calendar*" + yearAndMonth + "0" + i));
            } else if (i <= 9) {
                keyboardButtonsRow3.add(new InlineKeyboardButton("0" + i).setCallbackData("*button_number_calendar*" + yearAndMonth + "0" + i));

            } else if (i <= 16) {
                keyboardButtonsRow3.add(new InlineKeyboardButton("" + i).setCallbackData("*button_number_calendar*" + yearAndMonth + i));

            } else if (i <= 24) {
                keyboardButtonsRow4.add(new InlineKeyboardButton("" + i).setCallbackData("*button_number_calendar*" + yearAndMonth + i));

            } else {
                keyboardButtonsRow5.add(new InlineKeyboardButton("" + i).setCallbackData("*button_number_calendar*" + yearAndMonth + i));

            }
        }


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        //add all rows to keyboard
        rowList.add(keyboardButtonsRow0);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId, InlineKeyboardMarkup inlineKeyboardMarkup) {


        SendMessage calendar = new SendMessage().setChatId(chatId).setText("Calendar").setReplyMarkup(inlineKeyboardMarkup);
        return calendar;
    }


}
