package org.solarsystem.web.view;

import java.time.LocalDate;
import java.util.List;

public class CalculatorView {
    public String getCalculatorPage(String[] planetName) {
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        return indexSingleton.getIndex()
                .replace("<!--calculator-->",
                        indexSingleton.getCalculator());
    }

    public String getCalcPageForDoPost(List<String> planetNames, String from, String to, LocalDate date, double distance){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        String message = "";
        if (from != null && to != null){
            message = "On this date: "+date+" distance from Solar system body: "+from+
                    "   to solar system body: "+to+"  will be: "+distance+" kilometers or ten to the eighth degree"+"\n" +
                    "\n" ;
        }

        StringBuilder listPlanet = new StringBuilder();
        for (String s: planetNames
        ) {
            listPlanet.append("<option value=\""+s+"\">"+s+"</option>\n");
        }
         String page = "<div class=\"container-fluid bg-dark\" id=\"calculator\">\n" +
                    "        <a class=\"nav-link\" data-toggle=\"tab\" href=\"#CalculatorMenu\">Calculator</a>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div id=\"CalculatorMenu\" class=\"container-fluid tab-pane fade text-white bg-dark\"><br>\n" +
                    "    <form id=\"calc\" method=\"post\" action=\"/calculator\">\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col-md-2 mb-3\">\n" +
                    "            <label for=\"planet1\"><h3>From this body</h3></label>\n" +
                    "            <select class=\"custom-select d-block w-100\" id=\"planet1\" name=\"FromPlanet\" required>\n" +
                    "                <option value=\"\">Choose...</option>\n" +listPlanet+"<br>"+
                    "            </select>\n" +
                    "            <div class=\"invalid-feedback\">\n" +
                    "                Please select planet.\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-md-2 mb-3\">\n" +
                    "            <label for=\"planet2\"><h3>To this body</h3></label>\n" +
                    "            <select class=\"custom-select d-block w-100\" id=\"planet2\" name=\"ToPlanet\" required>\n" +
                    "                <option value=\"\">Choose...</option>\n" +listPlanet+"<br>"+
                    "            </select>\n" +
                    "            <div class=\"invalid-feedback\">\n" +
                    "                Please select planet.\n" +
                    "            </div></p>\n" +
                    "            <p>\n" +
                    "        </div>\n" +
                    "<label for=\"start\">Date:</label>\n" +
                    "\n" +
                    "<input type=\"date\" id=\"start\" name=\"date\"\n" +
                    "       value=\"2020-02-27\"\n" +
                    "       min=\"2000-02-27\" max=\"5020-02-27\">"+"<br>"+
                    "            <input type=\"submit\" value=\"Start\">\n" +"<br>"+
                    "           <p><textarea name=\"textarea\" cols=\"40\" rows=\"5\">\n" + message+
                    "    </textarea></p> \n"+
                    "    </div>\n" +
                    "    </form>\n" +
                    "</div>";
        return indexSingleton.getIndex().replace("<!--calculator-->",page);
    }
}


