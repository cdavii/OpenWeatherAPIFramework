package org.sparta.tests.FrameworkTests;

import org.junit.jupiter.api.*;
import org.sparta.Config;
import org.sparta.ConnectionManager;

public class ConnectionManagerTests {

    private final int STATUS_CODE_OK = 200;
    private final int STATUS_CODE_BAD_REQUEST = 400;
    private final int STATUS_CODE_NOT_FOUND = 404;

    @BeforeEach
    void setup() {
        ConnectionManager.resetUrlBuilders();
    }

    //Create a method for assumption to be passed in
    @Test
    @DisplayName("Check the city name URL is correct")
    void checkTheCityNameUrlIsCorrect() {
        ConnectionManager.changeQuerySearch("q=");
        ConnectionManager.buildCityNameUrl("London");
        Assumptions.assumeTrue(ConnectionManager.getStatusCode() == STATUS_CODE_OK);
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?q=London&appid=" + Config.getApiKey(), ConnectionManager.getConnection());
    }

    //api.openweathermap.org/data/2.5/weather?q={city name},{state code}&appid={API key}
    @Test
    @DisplayName("Check that state code URL is correct")
    void checkThatStateCodeUrlIsCorrect() {

    }

    @Test
    @DisplayName("Check that the country code URL is correct")
    void checkThatTheCountryCodeUrlIsCorrect() {
        ConnectionManager.changeQuerySearch("q=");
        ConnectionManager.buildCityNameUrl("London");
        ConnectionManager.buildCountryCodeUrl("uk");
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=" + Config.getApiKey(), ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the city id URL is correct ")
    void checkThatTheCityIdUrlIsCorrect() {
        ConnectionManager.changeQuerySearch("id=");
        ConnectionManager.buildCityIdUrl("2172797");
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?id=2172797&appid=" + Config.getApiKey(), ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the latitude and longitude URl is correct")
    void checkThatTheLatitudeAndLongitudeURlIsCorrect() {
        ConnectionManager.buildLatAndLongUrl(50.805832, -1.087222);
        ConnectionManager.getConnection();
        Assumptions.assumeTrue(ConnectionManager.getStatusCode() == STATUS_CODE_OK);
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?lat=50.805832&lon=-1.087222&appid=" + Config.getApiKey(), ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the zip code URL is correct")
    void checkThatTheZipCodeUrlIsCorrect() {
        ConnectionManager.buildZipCodeUrl("94040");
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?zip=94040&appid=" + Config.getApiKey(), ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the status code is 200")
    void checkThatTheStatusCodeIs200() {
        ConnectionManager.changeQuerySearch("q=");
        ConnectionManager.buildCityNameUrl("London");
        ConnectionManager.getConnection();
        Assertions.assertEquals(STATUS_CODE_OK, ConnectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Check that the status code is 400")
    void checkThatTheStatusCodeIs400() {
        ConnectionManager.getConnection();
        Assertions.assertEquals(STATUS_CODE_BAD_REQUEST, ConnectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Check that the status code is 404")
    void checkThatTheStatusCodeIs404() {
        ConnectionManager.changeQuerySearch("q=");
        ConnectionManager.buildCityNameUrl("rty");
        ConnectionManager.getConnection();
        Assertions.assertEquals(STATUS_CODE_NOT_FOUND, ConnectionManager.getStatusCode());
    }
}
