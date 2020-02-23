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

    public String getCalcPageForDoPost(List<String> planetName, String from, String to, LocalDate date, double distance){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        String message = "";
        String page = "";
        StringBuilder listPlanet = new StringBuilder();
        for (String s: planetName
        ) {
            listPlanet.append("<option value=\""+s+"\">"+s+"</option>\n");
        }
        if (from != null && to != null){

            message = "                <p>In this date: "+date+" Distance from this planet: "+from+
                    " To this planet: "+to+" Will be: "+distance+" Astronomical unit"+"\n</p>" +
                    "\n" ;

            page = "<div class=\"container-fluid bg-dark\" id=\"calculator\">\n" +
                    "        <a class=\"nav-link\" data-toggle=\"tab\" href=\"#CalculatorMenu\">Calculator</a>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div id=\"CalculatorMenu\" class=\"container-fluid tab-pane fade text-white bg-dark\"><br>\n" +
                    "    <form id=\"calc\" method=\"post\" action=\"/calculator\">\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col-md-2 mb-3\">\n" +
                    "            <label for=\"planet1\"><h3>From this planet</h3></label>\n" +
                    "            <select class=\"custom-select d-block w-100\" id=\"planet1\" name=\"FromPlanet\" required>\n" +
                    "                <option value=\"\">Choose...</option>\n" +listPlanet+
                    "            </select>\n" +
                    "            <div class=\"invalid-feedback\">\n" +
                    "                Please select planet.\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-md-2 mb-3\">\n" +
                    "            <label for=\"planet2\"><h3>To this planet</h3></label>\n" +
                    "            <select class=\"custom-select d-block w-100\" id=\"planet2\" name=\"ToPlanet\" required>\n" +
                    "                <option value=\"\">Choose...</option>\n" +listPlanet+
                    "            </select>\n" +
                    "            <div class=\"invalid-feedback\">\n" +
                    "                Please select planet.\n" +
                    "            </div></p>\n" +
                    "            <p>\n" +
                    "        </div>\n" +
                    "            <div class=\"input-group mb-3\">\n" +
                    "                <input name=\"year\" type=\"number\" min=\"2000\" max=\"9000\" value=\"2020\">\n" +
                    "                <input name=\"month\" type=\"number\" min=\"1\" max=\"12\" value=\"02\">\n" +
                    "                <input name=\"day\" type=\"number\" min=\"1\" max=\"31\" value=\"27\">\n" +
                    "            </div>\n" +
                    "            <input type=\"submit\" value=\"Start\">\n" +message+
                    "    </div>\n" +
                    "    </form>\n" +
                    "</div>";
        } else {
            message = "" +
                    "\n" ;

            page = "<div class=\"container-fluid bg-dark\" id=\"calculator\">\n" +
                    "        <a class=\"nav-link\" data-toggle=\"tab\" href=\"#CalculatorMenu\">Calculator</a>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div id=\"CalculatorMenu\" class=\"container-fluid tab-pane fade text-white bg-dark\"><br>\n" +
                    "    <form id=\"calc\" method=\"post\" action=\"/calculator\">\n" +
                    "    <div class=\"row\">\n" +
                    "        <div class=\"col-md-2 mb-3\">\n" +
                    "            <label for=\"planet1\"><h3>From this planet</h3></label>\n" +
                    "            <select class=\"custom-select d-block w-100\" id=\"planet1\" name=\"FromPlanet\" required>\n" +
                    "                <option value=\"\">Choose...</option>\n" +listPlanet+
                    "            </select>\n" +
                    "            <div class=\"invalid-feedback\">\n" +
                    "                Please select planet.\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-md-2 mb-3\">\n" +
                    "            <label for=\"planet2\"><h3>To this planet</h3></label>\n" +
                    "            <select class=\"custom-select d-block w-100\" id=\"planet2\" name=\"ToPlanet\" required>\n" +
                    "                <option value=\"\">Choose...</option>\n" +listPlanet+
                    "            </select>\n" +
                    "            <div class=\"invalid-feedback\">\n" +
                    "                Please select planet.\n" +
                    "            </div></p>\n" +
                    "            <p>\n" +
                    "        </div>\n" +
                    "            <div class=\"input-group mb-3\">\n" +
                    "                <input name=\"year\" type=\"number\" min=\"2000\" max=\"9000\" value=\"2020\">\n" +
                    "                <input name=\"month\" type=\"number\" min=\"1\" max=\"12\" value=\"02\">\n" +
                    "                <input name=\"day\" type=\"number\" min=\"1\" max=\"31\" value=\"27\">\n" +
                    "            </div>\n" +
                    "            <input type=\"submit\" value=\"Start\">\n" +message+
                    "    </div>\n" +
                    "    </form>\n" +
                    "</div>";

        }


        return indexSingleton.getIndex()
                .replace("<!--calculator-->",page);

    }
}


