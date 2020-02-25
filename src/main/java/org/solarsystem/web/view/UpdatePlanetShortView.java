package org.solarsystem.web.view;

import org.solarsystem.web.dao.entity.Planet;

public class UpdatePlanetShortView {
    public String getPageUpdatePlanet(Planet planet, String loginEmail, String loginPassword){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<form method=\"post\" action=\"/updatePlanetShortAdm\">\n" +
                "    <div class=\"form-group\">\n" +
                "        <label for=\"exampleInputEmail1\">Add short description</label>\n" +
                "        <input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\"\n" +
                "               aria-describedby=\"emailHelp\" name=\"shortDescription\">\n" +
                "        <input type=\"hidden\" name=\"idPlanet\" value=\""+planet.getId()+"\">\n" +
                "<input type=\"hidden\" name=\"loginEmail\" value=\""+loginEmail+"\">"+
                "<input type=\"hidden\" name=\"loginPassword\" value=\""+loginPassword+"\">"+
                "    </div>\n" +
                "    <button type=\"submit\" class=\"btn btn-primary\" >Submit</button>\n" +
                "</form>");
        return indexSingleton.getIndex().replace("<!--admin-->",stringBuilder.toString());
    }
}
