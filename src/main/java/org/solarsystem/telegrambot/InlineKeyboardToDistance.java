package org.solarsystem.telegrambot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
/**
 * Returns the inline keyboard with space body list.
 * Space body list is list with name of space body which are available in calculate distance between bodies.
 */
public class InlineKeyboardToDistance {
    SendMessage sendMessage(long chatId) {
        List<String> allSpaceBodyNames = new BotsServiceImpl().getAllSpaceBodyNames();
        InlineKeyboardBuilder inlineKeyboardBuilder = InlineKeyboardBuilder.create(chatId).setText("Make a choice");
        int countRow = allSpaceBodyNames.size() / 6 + 2;
        int countPlanetInList = 0;
        for (int i = 0; i <= countRow; i++) {
            inlineKeyboardBuilder.row();
            for (int rowSize = 0; rowSize < 5 && countPlanetInList < allSpaceBodyNames.size(); rowSize++, countPlanetInList++) {
                inlineKeyboardBuilder.button(allSpaceBodyNames.get(countPlanetInList), "*Planet_firts_name_is:*" + allSpaceBodyNames.get(countPlanetInList));
            }
            inlineKeyboardBuilder.endRow();
            inlineKeyboardBuilder.row();
            if (i == countRow) {
                inlineKeyboardBuilder.button("Choose the date", "*Choose_date_is:*" + "Choose the date");
            }
            inlineKeyboardBuilder.endRow();
        }
        return inlineKeyboardBuilder.build();
    }
}