package org.sparta.tests.APITests;

import org.junit.jupiter.api.*;
import org.sparta.ConnectionManager;
import org.sparta.Injector;
import org.sparta.dtos.WeatherDTO;
import org.sparta.utilities.CollectionChecker;


public class WeatherDTOTests {
    WeatherDTO weatherDTO = new WeatherDTO();

    @BeforeEach
    void setup(){
        ConnectionManager.setQueryType("q=");
        ConnectionManager.setCityNameQuery("London");
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

    @Nested
    @DisplayName("WeatherItem Tests")
    class WeatherItemTests {
        @Test
        @DisplayName("Test that there is at least one weather type")
        void testThatThereIsAtLeastOneWeatherType() {
            Assertions.assertFalse(weatherDTO.getWeather().isEmpty());
        }

        @Test
        @DisplayName("Test that each type of weather appears only once in the list")
        void testThatEachTypeOfWeatherAppearsOnlyOnceInTheList() {
            Assertions.assertFalse(CollectionChecker.containsDuplicates(weatherDTO.getWeather()));
        }
    }
}
