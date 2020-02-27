package org.solarsystem.web.view;

/*This is body page
uploadImageServlet
for admin */
public class UploadImageView {
    public String getPageUploadImage(int idPlanet, String loginEmail, String loginPassword){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h1>File Upload</h1>\n" +
                "<form method=\"post\" action=\"uploadImageServlet\" enctype=\"multipart/form-data\">\n" +
                "    Select file to upload: <input type=\"file\" name=\"file\" size=\"60\" /><br />\n" +
                "        <input type=\"hidden\" name=\"idPlanet\" value=\""+idPlanet+"\">\n" +
                "<input type=\"hidden\" name=\"loginEmail\" value=\""+loginEmail+"\">"+
                "<input type=\"hidden\" name=\"loginPassword\" value=\""+loginPassword+"\">"+
                "    <br /> <input type=\"submit\" value=\"Upload\" />\n" +
                "</form>");
        return indexSingleton.getIndex().replace("<!--admin-->",stringBuilder.toString());
    }
}
