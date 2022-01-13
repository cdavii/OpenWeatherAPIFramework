package org.sparta;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionManager {

    private final static String END_POINT = "&appid=" + Config.getApiKey();
    private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static String queryType;
    private static String cityNameQuery;
    private static String stateCodeQuery;
    private static String countryCodeQuery;
    private static String cityIdQuery;
    private static String latitudeQuery;
    private static String longitudeQuery;
    private static String zipCodeQuery;
    private static String completeUrl;

    public static int getStatusCode(){
        return getConnectionResponse().statusCode();
    }

    public static void resetUrlBuilders() {
        queryType = "";
        latitudeQuery = "";
        longitudeQuery = "";
        cityNameQuery = "";
        stateCodeQuery = "";
        countryCodeQuery = "";
        cityIdQuery = "";
        zipCodeQuery = "";
    }

    public static String getConnection(){
        completeUrl = BASE_URL + queryType +
                cityNameQuery  + stateCodeQuery + zipCodeQuery + countryCodeQuery  +
                cityIdQuery +
                latitudeQuery + longitudeQuery
                + END_POINT
                ;
        getConnectionResponse();
        //System.out.println(getConnectionResponse().uri().toString());
        return completeUrl;
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

    public static void changeQuerySearch(String query){
        queryType = query;
    }

    public static void buildCityIdUrl(String cityId) {
        cityIdQuery = cityId;
    }

    //need to fix the
    public static void buildLatAndLongUrl(Number latitude, Number longitude){
        latitudeQuery = "lat=" + latitude;//String.format("%.0f", latitude);
        //System.out.println(String.format("%.0f", latitude));
        longitudeQuery = "&lon=" + longitude;//String.format("%.0f", longitude);
        //System.out.println(String.format("%.0f", longitude));
    }

    public static void buildZipCodeUrl(String zipCode){
        zipCodeQuery = "zip=" + zipCode;
    }

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
