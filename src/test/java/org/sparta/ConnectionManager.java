package org.sparta;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionManager {

    private final static String API_KEY = Config.getApiKey();
    private final static String END_POINT = "&appid=" + API_KEY;
    private final static String BASE_URL = "api.openweathermap.org/data/2.5/weather?";
    private static String query= "q=";
    private static String cityNameQuery, stateCodeQuery, countryCodeQuery;
    private static double latitude, longitude;
    private static String completeUrl = BASE_URL;

    public static int getStatusCode(){
        return getConnectionResponse().statusCode();
    }

    public static String getConnection(){
        completeUrl = BASE_URL + query + cityNameQuery + stateCodeQuery + countryCodeQuery + END_POINT;
        getConnectionResponse();
        return completeUrl;
        //return getConnectionResponse().uri().toString();
    }

    public static void buildCityNameUrl(String cityName){
        cityNameQuery = cityName;
    }

    public static void buildStateCodeUrl(String stateCode){
        stateCodeQuery = "," + stateCode;
    }

    public static void buildCountryCodeUrl(String countryCode){
        countryCodeQuery = "," + countryCode;
    }

    public static void changeQuerySearch(){}

    private static HttpResponse<String> getConnectionResponse() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(completeUrl))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}
