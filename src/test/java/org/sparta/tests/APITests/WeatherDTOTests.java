package org.sparta.tests.APITests;

import org.junit.jupiter.api.*;
import org.sparta.ConnectionManager;
import org.sparta.Injector;
import org.sparta.dtos.WeatherDTO;
import org.sparta.utilities.CollectionChecker;
import org.sparta.utilities.NumberChecker;
import org.sparta.utilities.WeatherChecker;

import static org.junit.jupiter.api.Assertions.fail;


public class WeatherDTOTests {
    WeatherDTO weatherDTO = new WeatherDTO();

    @BeforeEach
    void setup(){
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

        @Test
        @DisplayName("Test that the weatherItem object is in the table of weather types")
        void testThatTheWeatherItemObjectIsInTheTableOfWeatherTypes() {
            int wCode = weatherDTO.getWeather().get(0).getId();
            String wKeyWord = weatherDTO.getWeather().get(0).getMain();
            String wDescription = weatherDTO.getWeather().get(0).getDescription();
            String wIcon = weatherDTO.getWeather().get(0).getIcon();

            Assertions.assertTrue(WeatherChecker.isValid(wCode,wKeyWord,wDescription,wIcon));
        }
    }

    @Test
    @DisplayName("Test that visibility is positive")
    void testThatVisibilityIsPositive() {
        Assertions.assertTrue(NumberChecker.isPositive(weatherDTO.getVisibility()));
    }

    @Nested
    @DisplayName("Snow Tests")
    class SnowTests{

        @Test
        @DisplayName("Test that snowfall for last 3h is in acceptable range")
        void testThatSnowfallForLast3HIsInAcceptableRange() {
            Assertions.assertTrue(NumberChecker.withinRange(0, 6000, weatherDTO.getSnow().getLastThreeHours()));
        }

        @Test
        @DisplayName("Test that snowfall for last hour is in acceptable range")
        void testThatSnowfallForLastHourIsInAcceptableRange() {
            Assertions.assertTrue(NumberChecker.withinRange(0,2000,weatherDTO.getSnow().getLastHour()));
        }

    }
}
