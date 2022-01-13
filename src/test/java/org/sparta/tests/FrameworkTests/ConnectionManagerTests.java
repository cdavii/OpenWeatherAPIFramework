package org.sparta.tests.FrameworkTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.Config;
import org.sparta.ConnectionManager;

public class ConnectionManagerTests {

    @Test
    @DisplayName("Check the URL passed")
    void checkTheUrlPassed() {
        ConnectionManager.buildCityNameUrl("London");
        Assertions.assertEquals("https://api.openweathermap.org/data/2.5/weather?q=London&appid="+ Config.getApiKey(), ConnectionManager.getConnection());
    }
}
