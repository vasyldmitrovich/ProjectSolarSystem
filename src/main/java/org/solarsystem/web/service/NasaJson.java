package org.solarsystem.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.solarsystem.web.service.jsonparser.JsonResponseResultID;
import org.solarsystem.web.service.jsonparser.ResultResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NasaJson implements DistanceCalculator{


    private static String calcId = null;
    private  double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static void main(String[] args) throws IOException {

    }

    public  void myGETRequest() throws IOException {
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

            //reading response in json format and deserializing using jackson
            String json = response.toString();
            ObjectMapper mapper = new ObjectMapper();
            ResultResponse resultResponse= mapper.readValue(json, ResultResponse.class);
            String[][] rows2 = resultResponse.getRows();

            distance = Double.valueOf(rows2[0][1]);

        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    public  void myPOSTRequest(String originPlanet, String destinationPlanet, String date) throws IOException {

        //sending form with data to NASA to get a response back
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
            //reading json response to get response id for using in the GET method
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            String jackson = response.toString();
            JsonResponseResultID jsonResponseResultID = new ObjectMapper().readerFor(JsonResponseResultID.class).readValue(jackson);
            calcId = jsonResponseResultID.getCalculationId();
        }
    }

    //return distance between planet in au
    @Override
    public double calculateDistance(String originPlanet, String destinationPlanet, LocalDate date) {
        if (!isPlanetToCalc(originPlanet) || (!isPlanetToCalc(destinationPlanet))){
            return -1.0;
        }
        String strDate = date.getYear()+"-"+((date.getMonthValue()<10)? "0"+date.getMonthValue():date.getMonthValue())+"-"
                +((date.getDayOfMonth()<10)? "0"+date.getDayOfMonth():date.getDayOfMonth());;

        try {
           myPOSTRequest(originPlanet.toUpperCase(),destinationPlanet.toUpperCase(), strDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myGETRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //convert distance from km to au
        distance = distance/149598000;
        return distance;
    }

    //return available list planetName for calculating the distance
    @Override
    public List<String> getAvailablePlanet() {
        List<PlanetName> planetNames = Arrays.asList(PlanetName.values());
        return planetNames.stream().map(Enum::toString).map(String::toLowerCase).collect(Collectors.toList());

    }

    //check planatname with list of available planetname
    public boolean isPlanetToCalc(String planetName){
       return getAvailablePlanet().contains(planetName.toLowerCase());
    }

}