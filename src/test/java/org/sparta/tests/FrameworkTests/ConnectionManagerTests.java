package org.sparta.tests.FrameworkTests;

import org.junit.jupiter.api.*;
import org.sparta.ConnectionManager;

public class ConnectionManagerTests {

    private final static int STATUS_CODE_OK = 200;
    private final static int STATUS_CODE_BAD_REQUEST = 400;
    private final static int STATUS_CODE_NOT_FOUND = 404;

    private void assumptionTestForConnection() {
        ConnectionManager.getConnection();
        Assumptions.assumeTrue(ConnectionManager.getStatusCode() == STATUS_CODE_OK);
    }

    @BeforeEach
    void setup() {
        ConnectionManager.resetUrlBuilders();
    }

    @Test
    @DisplayName("Check the city name URL is correct")
    void checkTheCityNameUrlIsCorrect() {
        //ConnectionManager.setQueryType("q=");
        ConnectionManager.setCityNameQuery("London");
        assumptionTestForConnection();
        Assertions.assertEquals(ConnectionManager.BASE_URL+ "q=London" + ConnectionManager.END_POINT, ConnectionManager.getCompleteUrl());
    }

    //State code is not made clear in the documentation, so it is not tested
//    @Test
//    @DisplayName("Check that state code URL is correct")
//    void checkThatStateCodeUrlIsCorrect() {
//
//    }

    @Test
    @DisplayName("Check that the country code URL is correct")
    void checkThatTheCountryCodeUrlIsCorrect() {
        ConnectionManager.setCityNameQuery("London");
        ConnectionManager.setCountryCodeQuery("uk");
        assumptionTestForConnection();
        Assertions.assertEquals(ConnectionManager.BASE_URL + "q=London,uk" + ConnectionManager.END_POINT, ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the city id URL is correct ")
    void checkThatTheCityIdUrlIsCorrect() {
        ConnectionManager.setCityIdQuery("2172797");
        assumptionTestForConnection();
        Assertions.assertEquals(ConnectionManager.BASE_URL + "id=2172797" + ConnectionManager.END_POINT, ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the latitude and longitude URl is correct")
    void checkThatTheLatitudeAndLongitudeURlIsCorrect() {
        ConnectionManager.setLatitudeQuery("50.805832");
        ConnectionManager.setLongitudeQuery("-1.087222");
        assumptionTestForConnection();
        Assertions.assertEquals(ConnectionManager.BASE_URL + "lat=50.805832&lon=-1.087222" + ConnectionManager.END_POINT, ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check that the zip code URL is correct")
    void checkThatTheZipCodeUrlIsCorrect() {
        ConnectionManager.setZipCodeQuery("94040");
        assumptionTestForConnection();
        Assertions.assertEquals(ConnectionManager.BASE_URL + "zip=94040" + ConnectionManager.END_POINT, ConnectionManager.getConnection());
    }

    @Test
    @DisplayName("Check metric parameter included in the URL")
    void checkMetricParameterIncludedInTheUrl() {
        ConnectionManager.setCityNameQuery("London");
        ConnectionManager.setMetricQuery(true);
        ConnectionManager.getConnection();
        System.out.println(ConnectionManager.getCompleteUrl());
        Assertions.assertEquals(ConnectionManager.BASE_URL + "q=London&units=metric" + ConnectionManager.END_POINT, ConnectionManager.getCompleteUrl());
    }

    @Test
    @DisplayName("Check imperial parameter included in the URL")
    void checkImperialParameterIncludedInTheUrl() {
        ConnectionManager.setCityNameQuery("London");
        ConnectionManager.setImperialQuery(true);
        ConnectionManager.getConnection();
        System.out.println(ConnectionManager.getCompleteUrl());
        Assertions.assertEquals(ConnectionManager.BASE_URL + "q=London&units=imperial" + ConnectionManager.END_POINT, ConnectionManager.getCompleteUrl());
    }

    @Test
    @DisplayName("Check that the status code is 200")
    void checkThatTheStatusCodeIs200() {
        ConnectionManager.setCityNameQuery("London");
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
        ConnectionManager.setCityNameQuery("rty");
        ConnectionManager.getConnection();
        Assertions.assertEquals(STATUS_CODE_NOT_FOUND, ConnectionManager.getStatusCode());
    }
}
