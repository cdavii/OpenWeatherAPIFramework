package org.sparta.tests.FrameworkTests;

import org.junit.jupiter.api.*;
import org.sparta.Config;
import org.sparta.ConnectionManager;

public class ConnectionManagerTests {

    private final int STATUS_CODE_OK = 200;
    private final int STATUS_CODE_BAD_REQUEST = 400;
    private final int STATUS_CODE_NOT_FOUND = 404;

    @Test
    @DisplayName("Check the URL passed")
    void checkTheUrlPassed() {
        ConnectionManager.buildCityNameUrl("London");
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?q=London&appid="+ Config.getApiKey(), ConnectionManager.getConnection());
    }

    @Nested
    @DisplayName("Status code tests")
    class StatusCodeTests {
        @BeforeEach
        void setup(){
            ConnectionManager.buildCityNameUrl("");
        }

        @Test
        @DisplayName("Check that the status code is 200")
        void checkThatTheStatusCodeIs200() {
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
            ConnectionManager.buildCityNameUrl("rty");
            ConnectionManager.getConnection();
            Assertions.assertEquals(STATUS_CODE_NOT_FOUND, ConnectionManager.getStatusCode());

        }
    }


}
