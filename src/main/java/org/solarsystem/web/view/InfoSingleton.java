package org.solarsystem.web.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InfoSingleton {
    private String patch;
    private String info;

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
        }
        return stringBuilder.toString();
    }

    public void setPatch(String patch){
        this.patch = patch;
        this.info = getPartialHtml("index");
    }
}
