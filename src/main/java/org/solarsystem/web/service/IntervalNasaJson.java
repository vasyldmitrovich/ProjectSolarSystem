package org.solarsystem.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.solarsystem.web.service.jsonparser.ResultResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntervalNasaJson implements IntervalDistanceCalculator {


    private static String calcId = null;
    private static Object GetAndPost;

    private String dateMinInterval;

    public String getDateMinInterval() {
        return dateMinInterval;
    }

    public void setDateMinInterval(String dateMinInterval) {
        this.dateMinInterval = dateMinInterval;
    }

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static void main(String[] args) throws IOException {

        LocalDate dateStart = LocalDate.parse("2012-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateFinish = LocalDate.parse("2013-05-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       // System.out.println("distance" + new NasaJson().calculateDistance("sun", "nix", date));
        System.out.println("distance: " + new IntervalNasaJson().calculateIntervalDistance("mars", "earth", dateStart, dateFinish));
        System.out.println("date: "+ new IntervalNasaJson().calculateDateMinInterval("mars", "earth", dateStart, dateFinish));

    }


    public void myGETRequestInterval() throws IOException {
        URL urlForGetRequest = new URL("https://wgc2.jpl.nasa.gov:8443/webgeocalc/api/calculation/" + calcId + "/results");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");

        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            String json = response.toString();
            ObjectMapper mapper = new ObjectMapper();
            ResultResponse resultResponse = mapper.readValue(json, ResultResponse.class);
            String[][] rows2 = resultResponse.getRows();

            ArrayList<Double> distanceInterval = new ArrayList<>();
            for (int i = 0; i < rows2.length; i++) {
                distanceInterval.add(i, Double.valueOf(rows2[i][1]));
            }
           // System.out.println(rows2.length);
          //  System.out.println("ArrayList Min Value: " + Collections.min(distanceInterval));
          //  System.out.println("ArrayList Min Value is at index: "
          //          + distanceInterval.indexOf(Collections.min(distanceInterval)));
         //   System.out.println(rows2[distanceInterval.indexOf(Collections.min(distanceInterval))][0]);
            distance = Collections.min(distanceInterval);
            dateMinInterval = (rows2[distanceInterval.indexOf(Collections.min(distanceInterval))][0]);

        } else {
            System.out.println("GET NOT WORKED");
        }
    }


    public void myPOSTRequestInterval(String originPlanet, String destinationPlanet, String dateStart, String dateFinish) throws IOException {
        final String POST_PARAMS = "{\n" +
                "  \"kernels\": [\n" +
                "    {\n" +
                "      \"type\": \"KERNEL_SET\",\n" +
                "      \"id\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"timeSystem\": \"UTC\",\n" +
                "  \"timeFormat\": \"CALENDAR\",\n" +
                "  \"intervals\": [\n" +
                "    {\n" +
                "      \"startTime\": \"" + dateStart + "T08:24:00.000\",\n" +
                "      \"endTime\": \"" + dateFinish + "T08:24:00.000\"\n" +
                "    }\n" +
                "  ],\n" +
                // "  \"times\": [\n" +
                // "    \""+date+"T08:24:00.000\"\n" +
                // "  ],\n" +
                "  \"timeStep\": 1,\n" +
                "  \"timeStepUnits\": \"DAYS\",\n" +
                "  \"calculationType\": \"STATE_VECTOR\",\n" +
                "  \"target\": \"" + destinationPlanet + "\",\n" +
                "  \"observer\": \"" + originPlanet + "\",\n" +
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

    //return distance between planet in km


    @Override
    public double calculateIntervalDistance(String originPlanet, String destinationPlanet, LocalDate dateStart, LocalDate dateFinish) {
        if (!isPlanetToCalc(originPlanet) || (!isPlanetToCalc(destinationPlanet))) {
            return -1.0;
        }
        String strDate = dateStart.getYear() + "-" + ((dateStart.getMonthValue() < 10) ? "0" + dateStart.getMonthValue() : dateStart.getMonthValue()) + "-"
                + ((dateStart.getDayOfMonth() < 10) ? "0" + dateStart.getDayOfMonth() : dateStart.getDayOfMonth());
        ;

        String strDate2 = dateStart.getYear() + "-" + ((dateFinish.getMonthValue() < 10) ? "0" + dateFinish.getMonthValue() : dateFinish.getMonthValue()) + "-"
                + ((dateFinish.getDayOfMonth() < 10) ? "0" + dateFinish.getDayOfMonth() : dateFinish.getDayOfMonth());
        ;
        try {
            myPOSTRequestInterval(originPlanet.toUpperCase(), destinationPlanet.toUpperCase(), strDate, strDate2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myGETRequestInterval();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return distance;

    }

    public String calculateDateMinInterval(String originPlanet, String destinationPlanet, LocalDate dateStart, LocalDate dateFinish) {
        //if (!isPlanetToCalc(originPlanet) || (!isPlanetToCalc(destinationPlanet))) {
         //   return -1.0;
      //  }
        String strDate = dateStart.getYear() + "-" + ((dateStart.getMonthValue() < 10) ? "0" + dateStart.getMonthValue() : dateStart.getMonthValue()) + "-"
                + ((dateStart.getDayOfMonth() < 10) ? "0" + dateStart.getDayOfMonth() : dateStart.getDayOfMonth());
        ;

        String strDate2 = dateStart.getYear() + "-" + ((dateFinish.getMonthValue() < 10) ? "0" + dateFinish.getMonthValue() : dateFinish.getMonthValue()) + "-"
                + ((dateFinish.getDayOfMonth() < 10) ? "0" + dateFinish.getDayOfMonth() : dateFinish.getDayOfMonth());
        ;
        try {
            myPOSTRequestInterval(originPlanet.toUpperCase(), destinationPlanet.toUpperCase(), strDate, strDate2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myGETRequestInterval();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dateMinInterval;
    }


    //return available list planetName for calculating the distance
    @Override
    public List<String> getAvailablePlanet() {
        List<PlanetName> planetNames = Arrays.asList(PlanetName.values());
        return planetNames.stream().map(Enum::toString).map(String::toLowerCase).collect(Collectors.toList());

    }

    //check planatname with list of available planetname
    public boolean isPlanetToCalc(String planetName) {
        return getAvailablePlanet().contains(planetName.toLowerCase());
    }


}