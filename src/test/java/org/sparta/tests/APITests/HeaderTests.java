package org.sparta.tests.APITests;

import org.junit.jupiter.api.*;
import org.sparta.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class HeaderTests {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final HttpRequest REQUEST = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=London,UK&units=metric&appid=" + Config.getApiKey()))
            .build();
    private static HttpResponse<String> response = null;
    private static Map<String, List<String>> headerMap;

    @BeforeAll
    static void init() {
        try {
            response = CLIENT.send(REQUEST, HttpResponse.BodyHandlers.ofString());
            HttpHeaders headers = response.headers();
            headerMap = headers.map();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    @DisplayName("print the map of headers")
    void print() {
        System.out.println(headerMap);
    }

    @Test
    @DisplayName("Check that server is openresty")
    void checkThatServerIsOpenResty() {
        Assertions.assertEquals(headerMap.get("server").get(0), "openresty");
        //System.out.println(headerMap.get("Server"));
    }

    @Test
    @DisplayName("Check that Access-Control-Allow-Methods contains GET")
    void checkThatAccessControlAllowMethodsContainsGet() {
        Assertions.assertTrue(headerMap.get("access-control-allow-methods").get(0).contains("GET"));
        //System.out.println(headerMap.get("access-control-allow-methods").get(0).contains("GET"));
    }

    @Test
    @DisplayName("Check that Access-Control-Allow-Credentials is true")
    void checkThatAccessControlAllowCredentialsIsTrue() {
        Assertions.assertTrue(Boolean.parseBoolean(headerMap.get("access-control-allow-credentials").get(0)));
    }

    @Test
    @DisplayName("Check that Access-Control-Allow-Origin is *")
    void checkThatAccessControlAllowOriginIsAsterisk() {
        Assertions.assertEquals(headerMap.get("access-control-allow-origin").get(0), "*");
    }

    @Test
    @DisplayName("Check that Content-Length is equal to body length in bytes")
    void checkThatContentLengthIsEqualToBodyLength() {
        int responseLength = response.body().getBytes(StandardCharsets.UTF_8).length;
        int contentLength = Integer.parseInt(headerMap.get("content-length").get(0));

        Assertions.assertEquals(responseLength, contentLength);
    }

    @Test
    @DisplayName("Check that Connection is set to keep-alive")
    void checkThatConnectionIsKeepAlive() {
        Assertions.assertEquals(headerMap.get("connection").get(0), "keep-alive");
    }

    @Test
    @DisplayName("Check that Content-Type is correct")
    void checkThatContentTypeIsCorrect() {
        Assertions.assertEquals(headerMap.get("content-type").get(0), "application/json; charset=utf-8");
    }

    @Test
    @DisplayName("Check that X-Cache-Key contains query parameters")
    void checkThatXCacheKeyContainsParameters() {
        // The .contains block in this would contain the relevant getters used by connectionManager for building the URL
        Assertions.assertTrue(headerMap.get("x-cache-key").get(0).contains("/data/2.5/weather?q=london,uk&units=metric"));
    }

}
