package org.sparta.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ValueConverter {

    public static Double degreesCToF(Double celsius) {
        return roundTo4(celsius * (9.0/5.0) + 32.0);
    }

    public static Double degreesCToK(Double celsius) {
        return roundTo4(celsius + 273.15);
    }

    public static Double degreesFToC(Double fahrenheit) {
        return roundTo4((fahrenheit - 32.0) * (5.0/9.0));
    }

    public static Double degreesFtoK(Double fahrenheit) {
        return roundTo4((fahrenheit - 32.0) * 5.0/9.0 + 273.15);
    }

    public static Double degreesKtoC(Double kelvin) {
        return roundTo4(kelvin - 273.15) ;
    }

    public static Double degreesKtoF(Double kelvin) {
        return roundTo4((kelvin - 273.15) * 9.0/5.0 + 32);
    }

    public static LocalDate makeDateFromEpoch(int epoch) {
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static Double roundTo4(Double number) {
        return Math.round(number * 10000.0) / 10000.0;
    }

    @Nested
    @DisplayName("ValueConverterTests")
    public class ValueConverterTests {

        @Test
        @DisplayName("Test Zero Celsius to Kelvin ")
        void testZeroCelsiusToKelvin() {
            Assertions.assertEquals(273.15, ValueConverter.degreesCToK(0.0));
        }

        @Test
        @DisplayName("Test Zero Celsius to Fahrenheit")
        void testZeroCelsiusToFahrenheit() {
            Assertions.assertEquals(32.0, ValueConverter.degreesCToF(0.0));
        }

        @Test
        @DisplayName("Test Zero Fahrenheit to Celsius")
        void testZeroFahrenheitToCelsius() {
            Assertions.assertEquals(-17.7778, ValueConverter.degreesFToC(0.0));
        }

        @Test
        @DisplayName("Test One Fahrenheit to Celsius")
        void testOneFahrenheitToCelsius() {
            Assertions.assertEquals(-17.2222, ValueConverter.degreesFToC(1.0));
        }

        @Test
        @DisplayName("Test Zero Fahrenheit to Kelvin")
        void testZeroFahrenheitToKelvin() {
            Assertions.assertEquals(255.3722, ValueConverter.degreesFtoK(0.0));
        }

        @Test
        @DisplayName("Test Zero Kelvin to Celsius")
        void testZeroKelvinToCelsius() {
            Assertions.assertEquals(-273.15, ValueConverter.degreesKtoC(0.0));
        }

        @Test
        @DisplayName("Test Zero Kelvin to Fahrenheit")
        void testZeroKelvinToFahrenheit() {
            Assertions.assertEquals(-459.67, ValueConverter.degreesKtoF(0.0));
        }

        @Test
        @DisplayName("Test Current Epoch Is The Same as Local Date Now")
        void testCurrentCurrentEpochIsTheSameAsLocalDateNow() {
            Assertions.assertEquals(LocalDate.now(), makeDateFromEpoch((int) Instant.now().getEpochSecond()));
        }
    }

}
