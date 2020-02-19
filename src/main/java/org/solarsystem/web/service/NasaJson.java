package org.solarsystem.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NasaJson implements DistanceCalculator{


    private static String calcId = null;
    private static double distance;



    public static void main(String[] args) throws IOException {

        LocalDate date = LocalDate.parse("2020-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("distance" + new NasaJson().calculateDistance("Earth", "Venus", date));

    }
    public  void MyGETRequest() throws IOException {
        URL urlForGetRequest = new URL("https://wgc2.jpl.nasa.gov:8443/webgeocalc/api/calculation/"+calcId +"/results");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");

        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            String json = response.toString();
            ObjectMapper mapper = new ObjectMapper();
            ResultResponse resultResponse= mapper.readValue(json, ResultResponse.class);
            String[][] rows2 = resultResponse.getRows();

            distance = Double.valueOf(rows2[0][1]);

        } else {
            System.out.println("GET NOT WORKED");
        }
    }
    public  void MyPOSTRequest(String originPlanet, String destinationPlanet, String date) throws IOException {
        final String POST_PARAMS = "{\n" +
                "  \"kernels\": [\n" +
                "    {\n" +
                "      \"type\": \"KERNEL_SET\",\n" +
                "      \"id\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"timeSystem\": \"UTC\",\n" +
                "  \"timeFormat\": \"CALENDAR\",\n" +
                "  \"times\": [\n" +
                "    \""+date+"T08:24:00.000\"\n" +
                "  ],\n" +
                "  \"timeStep\": 1,\n" +
                "  \"timeStepUnits\": \"SECONDS\",\n" +
                "  \"calculationType\": \"STATE_VECTOR\",\n" +
                "  \"target\": \""+destinationPlanet+"\",\n" +
                "  \"observer\": \""+originPlanet+"\",\n" +
                "  \"referenceFrame\": \"J2000\",\n" +
                "  \"aberrationCorrection\": \"NONE\",\n" +
                "  \"stateRepresentation\": \"RECTANGULAR\"\n" +
                "}";
       // System.out.println(POST_PARAMS);
        URL obj = new URL("https://wgc2.jpl.nasa.gov:8443/webgeocalc/api/calculation/new");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");

        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        //System.out.println("POST Response Code :  " + responseCode);
        //System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result

        } else {

           // System.out.println("POST NOT WORKED");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            //System.out.println(response.toString());
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            String jackson = response.toString();
            JsonResponseResultID jsonResponseResultID = new ObjectMapper().readerFor(JsonResponseResultID.class).readValue(jackson);
            //System.out.println(jsonResponseResultID.getCalculationId());
            calcId = jsonResponseResultID.getCalculationId();


        }
    }

    @Override
    public double calculateDistance(String originPlanet, String destinationPlanet, LocalDate date) {
        String strDate = date.getYear()+"-"+((date.getMonthValue()<10)? "0"+date.getMonthValue():date.getMonthValue())+"-"
                +((date.getDayOfMonth()<10)? "0"+date.getDayOfMonth():date.getDayOfMonth());;
        NasaJson nasaJson = new NasaJson();
        try {
           nasaJson.MyPOSTRequest(originPlanet,destinationPlanet, strDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            nasaJson.MyGETRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return distance;
    }
}