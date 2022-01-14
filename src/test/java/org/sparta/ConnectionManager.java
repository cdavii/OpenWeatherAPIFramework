package org.sparta;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {

    //Assumption that
    public final static String END_POINT = "&appid=" + Config.getApiKey();
    public final static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";
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

    public static String getCityNameQuery() {
        return cityNameQuery;
    }

    public static void setCityNameQuery(String cityNameQuery) {
        if (cityNameQuery.equals("")) {
            ConnectionManager.cityNameQuery = "";
        } else {
            ConnectionManager.cityNameQuery = "q=" + cityNameQuery;
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
            ConnectionManager.cityIdQuery = "id=" + cityIdQuery;
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
        ConnectionManager.completeUrl = BASE_URL +
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

    public static HttpResponse<String> getConnectionResponse() {
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

    public static Map<String, List<String>> getConnectionHeaders() {
        HttpHeaders headers = getConnectionResponse().headers();
        return headers.map();
    }

    public static HashMap<String, String> getExpectedMap() {
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("server", "openresty");
        expectedMap.put("access-control-allow-methods", "GET, SET");
        expectedMap.put("access-control-allow-credentials", "true");
        expectedMap.put("access-control-allow-origin", "*");
        expectedMap.put("connection", "keep-alive");
        expectedMap.put("content-type", "application/json; charset=utf-8");
        expectedMap.put("x-cache-key",getCityNameQuery()  + getStateCodeQuery() + getZipCodeQuery() + getCountryCodeQuery() +
                getCityIdQuery() +
                getLatitudeQuery() + getLongitudeQuery() +
                getMetricQuery() + getImperialQuery());
        return expectedMap;
    }

    public static Boolean checkServer(String value) {
        return getConnectionHeaders().get("server").get(0).equals(value);
    }
    public static Boolean checkAccessControllAllowMethods(String value) {
        return getConnectionHeaders().get("access-control-allow-methods").get(0).contains(value);
    }

    public static Boolean checkAccessControlAllowCredentials(){
        return Boolean.parseBoolean(getConnectionHeaders().get("access-control-allow-credentials").get(0));
    }

    public static Boolean checkAccessControlAllowOrigin(String value){
        return getConnectionHeaders().get("access-control-allow-origin").get(0).equals(value);
    }

    public static Boolean checkBodyLengthInUtfEight() {
        int responseLength = getConnectionResponse().body().getBytes(StandardCharsets.UTF_8).length;
        int contentLength = Integer.parseInt(getConnectionHeaders().get("content-length").get(0));

        return responseLength == contentLength;
    }

    public static Boolean checkConnection(String value) {
        return getConnectionHeaders().get("connection").get(0).equals(value);
    }

    public static Boolean checkContentType(String value) {
        return getConnectionHeaders().get("content-type").get(0).equals(value);
    }

    public static Boolean checkXCacheKeyContainsQueries() {

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        String latitudeQuery = String.valueOf(decimalFormat.format(Double.parseDouble(getLatitudeQuery().replaceAll("lat=", ""))));
        String longitudeQuery = String.valueOf(decimalFormat.format(Double.parseDouble(getLongitudeQuery().replaceAll("&lon=", ""))));

        String queries = "?" + getCityNameQuery()  + getStateCodeQuery() + getZipCodeQuery() + getCountryCodeQuery() +
                getCityIdQuery() +
                "lat=" + latitudeQuery + "&" +
                "lon=" + longitudeQuery +
                getMetricQuery() + getImperialQuery();

        String value = getConnectionHeaders().get("x-cache-key").get(0).replaceAll("/data/2.5/weather", "");

        return queries.toLowerCase().contains(value.toLowerCase());
    }

    public static Boolean checkAgainstExpectedMap(String value) {
        return getConnectionHeaders().get(value.toLowerCase()).get(0).equals(getExpectedMap().get(value.toLowerCase()));

    }

}
