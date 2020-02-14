package org.solarsystem.web.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexSingleton {

    private String path;
    private String index;
    private String landing;

    private static IndexSingleton ourInstance = new IndexSingleton();

    public static IndexSingleton getInstance() {
        return ourInstance;
    }

    private IndexSingleton() {
    }

    public String getIndex() {
        return index;
    }

    public String getLanding() {
        return landing;
    }

    public void setPath(String path) {
        this.path = path;
        this.index = getPartialHtml("index");
        this.landing = getPartialHtml("landing");
    }

    private String getPartialHtml(String filename){
        StringBuilder strb = new StringBuilder();
        Path file = Paths.get(this.path + filename + ".html");
        Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                strb.append(line).append("\n");
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return strb.toString();
    }
}
