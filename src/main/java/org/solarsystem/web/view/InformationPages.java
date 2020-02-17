package org.solarsystem.web.view;

import org.solarsystem.web.dao.entity.Planet;

import java.util.List;

public class InformationPages {



    public String navigationTabs(List<Planet> planets){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"container-fluid bg-dark \">\n" +
                "\n" +
                "        <ul class=\"nav nav-tabs\" role=\"tablist\">"+
                "<li class=\"nav-item\">\n" +
                "                <a class=\"nav-link active\" data-toggle=\"tab\" href=\"#solarSystemMenu\">Solar system</a>\n" +
                "            </li>");
        for (Planet p: planets
             ) {
            stringBuilder.append("<li class=\"nav-item\">\n" +
                    "                <a class=\"nav-link\" data-toggle=\"tab\" href=\"#"+p.getName()+"Menu\">"+p.getName()+"</a>\n" +
                    "            </li>");
        }
        stringBuilder.append("</ul>\n" +
                "    </div>");
        return stringBuilder.toString();

    }

    public String tabContent(List<Planet> planets){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"tab-content\">\n" +
                "\n" +
                "    <div id=\"solarSystemMenu\" class=\"container-fluid tab-pane active text-white bg-dark \"><br>\n" +
                "\n" +
                "        <h3>Solar system</h3>\n" +
                "\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col-sm-4\">\n" +
                "\n" +
                "                <img src=\"resources/solarSystem.jpg\"  class=\"float-left\" width=\"500\" height=\"auto\"/>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"col-sm-8\" >\n" +
                "\n" +
                "                <p>Сонячна система — це система планет, у центрі якої розташована яскрава зірка, джерело енергії, тепла і світла — Сонце.\n" +
                "\n" +
                "                <p>За однією з теорій Сонце утворилося разом із Сонячною системою орієнтовно 4,5 мільярдів років тому, унаслідок вибуху однієї або декількох наднових зірок.\n" +
                "\n" +
                "                <p>Спочатку Сонячна система являла собою хмару з газу й частинок пилу, які в русі й під впливом своєї маси утворили диск, у якому виникла нова зірка Сонце і вся наша Сонячна система.\n" +
                "                    У центрі Сонячної системи розташоване Сонце, навколо якого по орбітах обертаються вісім великих планет. Оскільки Сонце зміщене від центру планетарних орбіт, то за цикл оберту навколо Сонця планети то наближаються, то віддаляються по своїх орбітах.\n" +
                "\n" +
                "\n" +
                "\n" +
                "                <p>Розрізняють дві групи планет:\n" +
                "                <p> Планети земної групи: Меркурій, Венера, Земля й Марс. Ці планети невеликого розміру з кам’янистою поверхнею, вони розташувалися найближче до Сонця.\n" +
                "\n" +
                "                <p>Планети Сонячної системи.\n" +
                "\n" +
                "\n" +
                "\n" +
                "                <p>Планети гіганти: Юпітер, Сатурн, Уран і Нептун. Це великі планети, що складаються в основному з газу і їм характерна наявність кілець, що складаються з крижаного пилу й безлічі кам’янистих шматків.\n" +
                "\n" +
                "                <p>Планети Сонячної системи.\n" +
                "\n" +
                "                <p>А ось Плутон не потрапляє в жодну групу, тому що, попри своє перебування в Сонячній системі, занадто далеко розташований від Сонця й має зовсім невеликий діаметр, усього 2320 км, що вдвічі менше діаметра Меркурія.\n" +
                "\n" +
                "                <p>Сонце являє собою гігантську вогненну кулю дуже високої температури, що складається з плазми (іонізованого газу) у складі з воднем і гелієм. </p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>");
        for (Planet p: planets
             ) {
            stringBuilder.append("<div id=\""+p.getName()+"Menu\" class=\"container-fluid tab-pane fade text-white bg-dark\"><br>\n" +
                    "\n" +
                    "        <h3>"+p.getName()+"</h3>\n" +
                    "\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col-sm-6\">\n" +
                    "\n" +
                    "                <div id=\"demo\" class=\"container-fluid carousel slide \" data-ride=\"carousel\">\n" +
                    "\n" +
                    "                    <!-- Indicators -->\n" +
                    "                    <ul class=\"carousel-indicators\">\n" +
                    "                        <li data-target=\"#demo\" data-slide-to=\"0\" class=\"active\"></li>\n" +
                    "                        <li data-target=\"#demo\" data-slide-to=\"1\"></li>\n" +
                    "                    </ul>\n" +
                    "\n" +
                    "                    <!-- The slideshow -->\n" +
                    "                    <div class=\"carousel-inner mx-auto d-block\">\n" +
                    "                        <div class=\"carousel-item active \">\n" +
                    "                            <img src=\"/images/resources/Mercury.jpg\" alt=\"Mercury1\" width=\"600\" height=auto>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"carousel-item\">\n" +
                    "                            <img src=\"/images/resources/Mercury2.jpg\" alt=\"Mercury2\" width=\"600\" height=auto>\n" +
                    "                        </div>\n" +
                    "\n" +
                    "                    </div>\n" +
                    "\n" +
                    "                    <!-- Left and right controls -->\n" +
                    "                    <a class=\"carousel-control-prev\" href=\"#demo\" data-slide=\"prev\">\n" +
                    "                        <span class=\"carousel-control-prev-icon\"></span>\n" +
                    "                    </a>\n" +
                    "                    <a class=\"carousel-control-next\" href=\"#demo\" data-slide=\"next\">\n" +
                    "                        <span class=\"carousel-control-next-icon\"></span>\n" +
                    "                    </a>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "\n" +
                    "            <div class=\"col-sm-6\" >\n" +
                    "                <p>"+p.getFullDescription()+"</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>");

        }

        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }
}
