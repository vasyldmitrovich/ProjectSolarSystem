package org.solarsystem.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NasaJson {


    private static String calcId = null;
    private static NasaJson GetAndPost;


    public static void main(String[] args) throws IOException {
        GetAndPost.MyPOSTRequest();
        GetAndPost.MyGETRequest();

    }
    public static void MyGETRequest() throws IOException {
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
            // print result
            System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());

            String json = response.toString();
            //simple parsing
            ObjectMapper mapper = new ObjectMapper();
            ResultResponse resultResponse= mapper.readValue(json, ResultResponse.class);
            List<Columns> columns = resultResponse.getColumns();
            String[][] rows2 = resultResponse.getRows();
            System.out.println("__________________________");
            for (int i =0; i<resultResponse.getColumns().size();i++){
                System.out.println(columns.get(i).getName().substring(0,columns.get(i).getName().indexOf(" "))+" "+rows2[0][i]+" "+columns.get(i).getUnits());
            }
            System.out.println("______________________");

            //



            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode rows = rootNode.path("rows");
            Iterator<JsonNode> elements = rows.elements();
            List<String> responseData = new ArrayList<>();
            while(elements.hasNext()){
                JsonNode data = elements.next();
                System.out.println("bunch of data: " +data.toString());
                responseData.add(data.toString());


            }
            //System.out.println(responseData.get(1));


        } else {
            System.out.println("GET NOT WORKED");
        }
    }
    public static void MyPOSTRequest() throws IOException {
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
                "    \"2017-07-19T08:24:00.000\"\n" +
                "  ],\n" +
                "  \"timeStep\": 1,\n" +
                "  \"timeStepUnits\": \"SECONDS\",\n" +
                "  \"calculationType\": \"STATE_VECTOR\",\n" +
                "  \"target\": \"SUN\",\n" +
                "  \"observer\": \"EARTH\",\n" +
                "  \"referenceFrame\": \"J2000\",\n" +
                "  \"aberrationCorrection\": \"NONE\",\n" +
                "  \"stateRepresentation\": \"RECTANGULAR\"\n" +
                "}";
        System.out.println(POST_PARAMS);
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
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
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

            System.out.println("POST NOT WORKED");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            System.out.println(response.toString());
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            String jackson = response.toString();
            JsonResponseResultID jsonResponseResultID = new ObjectMapper().readerFor(JsonResponseResultID.class).readValue(jackson);
            System.out.println(jsonResponseResultID.getCalculationId());
            calcId = jsonResponseResultID.getCalculationId();
            System.out.println(response.toString() + "END");

        }
    }
}