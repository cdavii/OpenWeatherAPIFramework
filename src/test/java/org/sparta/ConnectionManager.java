package org.sparta;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionManager {

    //Assumption that
    public final static String END_POINT = "&appid=" + Config.getApiKey();
    public final static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static String queryType = "";
    private static String cityNameQuery = "";
    private static String stateCodeQuery = "";
    private static String countryCodeQuery = "";
    private static String cityIdQuery = "";
    private static String latitudeQuery = "";
    private static String longitudeQuery = "";
    private static String zipCodeQuery = "";
    private static String metricQuery = "";
    private static String imperialQuery = "";
    private static String languageQuery = "";
    private static String completeUrl = "";

    public static String getQueryType() {
        return queryType;
    }

    public static void setQueryType(String queryType) {
        if (queryType.equals("")) {
            ConnectionManager.queryType = "";
        } else {
            ConnectionManager.queryType = queryType;
        }
    }

    public static String getCityNameQuery() {
        return cityNameQuery;
    }

    public static void setCityNameQuery(String cityNameQuery) {
        if (cityNameQuery.equals("")) {
            ConnectionManager.cityNameQuery = "";
        } else {
            ConnectionManager.cityNameQuery = cityNameQuery;
        }
    }

    public static String getStateCodeQuery() {
        return stateCodeQuery;
    }

    public static void setStateCodeQuery(String stateCodeQuery) {
        if (stateCodeQuery.equals("")) {
            ConnectionManager.stateCodeQuery = "";
        } else {
            ConnectionManager.stateCodeQuery = "," + stateCodeQuery;
        }
    }

    public static String getCountryCodeQuery() {
        return countryCodeQuery;
    }

    public static void setCountryCodeQuery(String countryCodeQuery) {
        if (countryCodeQuery.equals("")) {
            ConnectionManager.countryCodeQuery = "";
        } else {
            ConnectionManager.countryCodeQuery = "," + countryCodeQuery;
        }
    }

    public static String getCityIdQuery() {
        return cityIdQuery;
    }

    public static void setCityIdQuery(String cityIdQuery) {
        if (cityIdQuery.equals("")) {
            ConnectionManager.cityIdQuery = "";
        } else {
            ConnectionManager.cityIdQuery = cityIdQuery;
        }
    }

    public static String getLatitudeQuery() {
        return latitudeQuery;
    }

    public static void setLatitudeQuery(String latitudeQuery) {
        if (latitudeQuery.equals("")) {
            ConnectionManager.latitudeQuery = "";
        } else {
            ConnectionManager.latitudeQuery = "lat=" + latitudeQuery;
        }
    }

    public static String getLongitudeQuery() {
        return longitudeQuery;
    }

    public static void setLongitudeQuery(String longitudeQuery) {
        if (longitudeQuery.equals("")) {
            ConnectionManager.longitudeQuery = "";
        } else {
            ConnectionManager.longitudeQuery = "&lon=" + longitudeQuery;
        }
    }

    public static String getZipCodeQuery() {
        return zipCodeQuery;
    }

    public static void setZipCodeQuery(String zipCodeQuery) {
        if (zipCodeQuery.equals("")) {
            ConnectionManager.zipCodeQuery = "";
        } else {
            ConnectionManager.zipCodeQuery = "zip=" + zipCodeQuery;
        }
    }

    public static String getMetricQuery() {
        return metricQuery;
    }

    public static void setMetricQuery(Boolean isMetric) {
        if (isMetric) {
            ConnectionManager.metricQuery = "&units=metric";
        } else {
            ConnectionManager.metricQuery = "";
        }
    }

    public static String getImperialQuery() {
        return imperialQuery;
    }

    public static void setImperialQuery(Boolean isImperial) {
        if (isImperial) {
            ConnectionManager.imperialQuery = "&units=imperial";
        } else {
            ConnectionManager.imperialQuery = "";
        }
    }

    public static String getLanguageQuery() {
        return languageQuery;
    }

    public static void setLanguageQuery(String languageQuery) {
        if (languageQuery.equals("")) {
            ConnectionManager.languageQuery = "";
        } else {
            ConnectionManager.languageQuery = "&lang=" + languageQuery;
        }
    }


    public static String getCompleteUrl() {
        return completeUrl;
    }

    public static void setCompleteUrl() {
        ConnectionManager.completeUrl = BASE_URL + getQueryType() +
                getCityNameQuery()  + getStateCodeQuery() + getZipCodeQuery() + getCountryCodeQuery() +
                getCityIdQuery() +
                getLatitudeQuery() + getLongitudeQuery() +
                getMetricQuery() + getImperialQuery() +
                END_POINT +
                getLanguageQuery();
    }

    public static int getStatusCode(){
        return getConnectionResponse().statusCode();
    }

    public static void resetUrlBuilders() {
        setQueryType("");
        setCityIdQuery("");
        setCityNameQuery("");
        setLatitudeQuery("");
        setLongitudeQuery("");
        setStateCodeQuery("");
        setCountryCodeQuery("");
        setZipCodeQuery("");
        setMetricQuery(false);
        setImperialQuery(false);
        setLanguageQuery("");
    }

    public static String getConnection(){
        setCompleteUrl();
        getConnectionResponse();
        return getCompleteUrl();
    }

    private static HttpResponse<String> getConnectionResponse() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getCompleteUrl()))
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
