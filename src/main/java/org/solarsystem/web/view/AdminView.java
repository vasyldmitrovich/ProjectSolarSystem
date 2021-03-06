package org.solarsystem.web.view;

import org.solarsystem.web.dao.entity.Planet;

import java.util.List;
/*That class, building admin page*/
public class AdminView {
    public String getFullPage(List<Planet> planetNames, String loginEmail, String loginPassword){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h2 class=\"text-white\">We have planets:</h2>");
        stringBuilder.append("<a href='#'> Add New planet</a>");
        stringBuilder.append("<h1 class=\"text-white\" >Planets List</h1>");
        stringBuilder.append("<table border='1' width='100%' class=\"tableAdminInfo text-white\"");
        stringBuilder.append("<tr>\n" +
                "    <th>Id</th><th>Name</th><th>OrbitalPeriod</th><th>Diameter</th><th>Gravity</th>" +
                "<th>IsSatellites</th><th>ShortDescription</th><th>FullDescription</th><th>LanguageId</th><th>Images</th>\n" +
                "</tr>");
            /*Add in table each planet*/
        for (Planet p : planetNames
             ) {
            StringBuilder stringBuilderImages = new StringBuilder();
            List<String> listImages = p.getImage();
            for (String s: listImages
                 ) {
                stringBuilderImages.append(s+",");
            }
            stringBuilder.append("<tr>" +
                    "<td>"+p.getId()+"</td>"+
                    "<td>"+p.getName()+"</td>"+
                    "<td>"+p.getOrbitalPeriod()+"</td>"+
                    "<td>"+p.getDiameter()+"</td>"+
                    "<td>"+p.getGravity()+"</td>"+
                    "<td>"+p.isSatellites()+"</td>"+
                    "<td>"+p.getShortDescription()+"</td>"+
                    "<td>"+p.getFullDescription()+"</td>"+
                    "<td>"+p.getLanguageId()+"</td>"+
                    "<td>"+stringBuilderImages+"</td>"+
                    "<td><a href='updatePlanetShortAdm?id="+p.getId()+"&loginEmail="+loginEmail+
                    "&loginPassword="+loginPassword+"'>EDIT SHORT</a></td>"+
                    "<td><a href='updatePlanetFullAdm?id="+p.getId()+"&loginEmail="+loginEmail+
                    "&loginPassword="+loginPassword+"'>EDIT FULL</a></td>"+
                    "<td><a href='/deletePlanetAdm?id="+p.getId()+"&loginEmail="+loginEmail+
                    "&loginPassword="+loginPassword+"'>DELETE</a></td>"+
                    "<td><a href='/uploadImageServlet?id="+p.getId()+"&loginEmail="+loginEmail+
                    "&loginPassword="+loginPassword+"'>ADD IMAGE</a></td>"+
                    "</tr>");
        }
        stringBuilder.append("</table>");
        String fullPage = stringBuilder.toString();
        return indexSingleton.getIndex().replace("<!--admin-->",fullPage);
    }
}
