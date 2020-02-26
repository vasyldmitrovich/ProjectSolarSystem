package org.solarsystem.web.view;

import org.apache.log4j.Logger;
import org.solarsystem.web.controller.UpdatePlanetShortServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*This is singleton only for index.html*/
public class InfoSingleton {
    private String patch;
    private String info;
    public static final Logger log = Logger.getLogger(UpdatePlanetShortServlet.class);

    private static InfoSingleton infoSingleton = new InfoSingleton();

    public static InfoSingleton getInstance(){
        return infoSingleton;
    }

    private InfoSingleton(){}

    public String getInfo(){
        return info;
    }


    public String getPartialHtml(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        Path file = Paths.get(this.patch+fileName+".html");
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader bufferedReader = Files.newBufferedReader(file,charset)){
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e){
            System.err.format("IOException: %s%n", e);
            log.info("Read file index.html from disc, IOException: %s%n"+e);
        }
        return stringBuilder.toString();
    }

    public void setPatch(String patch){
        this.patch = patch;
        this.info = getPartialHtml("index");
    }
}
