package org.solarsystem.web.view;

import org.solarsystem.web.dao.entity.Planet;

import java.util.List;

public class InformationView {

    public String navigationTabs(List<Planet> planets){
        /*form the navigation bar*/
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
        /*form the tab content bar*/
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"tab-content\">");
        /*This is add only solar system menu*/
        stringBuilder.append("    <div id=\"solarSystemMenu\" class=\"container-fluid tab-pane active text-white bg-dark \"><br>\n" +
                "\n" +
                "        <h3>Solar system</h3>\n" +
                "\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col-sm-4\">\n" +
                "\n" +
                "                <img src=\"/images/resources/solarSystem.jpg\"  class=\"float-left\" width=\"500\" height=\"auto\"/>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"col-sm-8\" >\n" +
                "\n" +
                "                <p>Solar System - this is you and I :-)</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>");
        /*Form list images from DB*/
        for (Planet p: planets
             ) {
            StringBuilder stringBuilderImages = new StringBuilder();
            stringBuilderImages.append("<div class=\"carousel-inner mx-auto d-block\">");
            List<String> list = p.getImage();
            for (int i=0;i<list.size();i++){
                if (i==0){
                    stringBuilderImages.append("<div class=\"carousel-item active \">\n" +
                            "                                <img src=\""+list.get(i)+"\" alt=\"Mercury1\" width=\"600\" height=auto>\n" +
                            "                            </div>");
                } else {
                    stringBuilderImages.append("<div class=\"carousel-item \">\n" +
                            "                                <img src=\""+list.get(i)+"\" alt=\"Mercury1\" width=\"600\" height=auto>\n" +
                            "                            </div>");
                }
            }
            stringBuilderImages.append("</div>");
            /*Form full page*/
            stringBuilder.append("<div id=\""+p.getName()+"Menu\" class=\"container-fluid tab-pane fade text-white bg-dark\"><br>\n" +
                    "\n" +
                    "        <h3>"+p.getName()+"</h3>\n" +
                    "\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col-sm-6\">\n" +
                    "\n" +
                    "                <div id=\"demo"+p.getId()+"\" class=\"container-fluid carousel slide \" data-ride=\"carousel\">\n" +
                    "\n" +
                    "                    <!-- Indicators -->\n" +
                    "                    <ul class=\"carousel-indicators\">\n" +
                    "                        <li data-target=\"#"+"demo"+p.getId()+"\" data-slide-to=\"0\" class=\"active\"></li>\n" +
                    "                        <li data-target=\"#"+"demo"+p.getId()+"\" data-slide-to=\"1\"></li>\n" +
                    "                    </ul>\n" +
                    "\n" +
                    "                    <!-- The slideshow -->\n" +
                    "                    <div class=\"carousel-inner mx-auto d-block\">\n" +
                    stringBuilderImages
                    +
                    "\n" +
                    "                    </div>\n" +
                    "\n" +
                    "                    <!-- Left and right controls -->\n" +
                    "                    <a class=\"carousel-control-prev\" href=\"#"+"demo"+p.getId()+"\" data-slide=\"prev\">\n" +
                    "                        <span class=\"carousel-control-prev-icon\"></span>\n" +
                    "                    </a>\n" +
                    "                    <a class=\"carousel-control-next\" href=\"#"+"demo"+p.getId()+"\" data-slide=\"next\">\n" +
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

    public String returnFullPage(String fullBodyData){
        InfoSingleton infoSingleton = InfoSingleton.getInstance();
        return infoSingleton.getInfo().replace("<!--This is nav bar and tab content from DB-->",fullBodyData);

    }
}
