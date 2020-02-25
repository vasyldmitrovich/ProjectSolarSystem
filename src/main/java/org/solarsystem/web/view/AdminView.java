package org.solarsystem.web.view;

import org.solarsystem.web.dao.entity.Planet;

import java.util.List;

public class AdminView {
    public String getFullPage(List<Planet> planetNames){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h2 style=\"text-decoration-color: white\">We have planets:</h2>");
        for (Planet p : planetNames
             ) {
            StringBuilder stringBuilderImages = new StringBuilder();
            List<String> list = p.getImage();
            for (String s: list
                 ) {
                stringBuilderImages.append(s+",");
            }

            stringBuilder.append("<div class=\"alert alert-primary\" role=\"alert\">\n" +
                    "Planet{" +
                            "id=" + p.getId() +"\n<br>"+
                            ", name='" + p.getName() + '\'' +"\n<br>"+
                            ", orbitalPeriod=" + p.getOrbitalPeriod() +"\n<br>"+
                            ", diameter=" + p.getDiameter() +"\n<br>"+
                            ", gravity=" + p.getGravity() +"\n<br>"+
                            ", isSatellites=" + p.isSatellites() +"\n<br>"+
                            ", shortDescription='" + p.getShortDescription() + '\'' +"\n<br>"+
                            ", fullDescription='" + p.getFullDescription() + '\'' +"\n<br>"+
                            ", languageId='" + p.getLanguageId() + '\'' +"\n<br>"+
                            ", image=" + stringBuilderImages +"\n<br>"+
                            '}'+
                    "</div>");
        }

        String fullPage = stringBuilder.toString();
        return indexSingleton.getIndex().replace("<!--admin-->",fullPage);
    }
}
