package org.sparta.tests.APITests;

import org.junit.jupiter.api.*;
import org.sparta.ConnectionManager;
import org.sparta.Injector;
import org.sparta.dtos.WeatherDTO;


public class WeatherDTOTests {
    WeatherDTO weatherDTO = new WeatherDTO();

    @BeforeEach
    void setup(){
        ConnectionManager.buildCityNameUrl("London");
        System.out.println(ConnectionManager.getConnection());
        weatherDTO = Injector.injectDTO(ConnectionManager.getConnection());
    }

    @Nested
    @DisplayName("Coordinate Tests")
    class CoordinateTests {
        @Test
        @DisplayName("Longitude Range Test")
        void longitudeRangeTest() {
            Assertions.assertTrue(weatherDTO.getCoord().getLon() <= 180 && weatherDTO.getCoord().getLon() >= -180);
        }

        @Test
        @DisplayName("Latitude Range Test")
        void latitudeRangeTest() {
            Assertions.assertTrue(weatherDTO.getCoord().getLat() <= 90 && weatherDTO.getCoord().getLat() >= -90);
        }
    }
}
