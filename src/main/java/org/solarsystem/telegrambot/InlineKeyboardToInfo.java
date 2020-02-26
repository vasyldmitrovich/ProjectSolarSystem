package org.solarsystem.telegrambot;

import org.solarsystem.web.service.PlanetInfoImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

/**
 * Returns the inline keyboard with planet list.
 * Planet list is list with name of planet which are available in db
 */

public class InlineKeyboardToInfo {
    SendMessage sendMessage(long chatId) {
        List<String> allSpaceBodyNames = new PlanetInfoImpl().getAvailablePlanetFromDatabase();
        InlineKeyboardBuilder inlineKeyboardBuilder = InlineKeyboardBuilder.create(chatId).setText("Make a choice");
        int countRow = allSpaceBodyNames.size() / 5;
        int countPlanetInList = 0;
        for (int i = 0; i <= countRow; i++) {
            inlineKeyboardBuilder.row();
            for (int rowSize = 0; rowSize < 5 && countPlanetInList < allSpaceBodyNames.size(); rowSize++, countPlanetInList++) {

                inlineKeyboardBuilder.button(allSpaceBodyNames.get(countPlanetInList), "*Planet_name_is:*" + allSpaceBodyNames.get(countPlanetInList));
            }
            inlineKeyboardBuilder.endRow();
        }
        return inlineKeyboardBuilder.build();
    }

}