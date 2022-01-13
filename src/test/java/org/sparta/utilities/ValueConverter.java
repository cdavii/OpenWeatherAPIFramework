package org.sparta.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ValueConverter {

    public static Double degreesCToF(Double celsius) {
        return celsius * (9.0/5.0) + 32.0;
    }

    public static Double degreesCToK(Double celsius) {
        return celsius + 273.15;
    }

    public static Double degreesFToC(Double fahrenheit) {
        return (fahrenheit - 32.0) * 5.9;
    }

    public static Double degreesFtoK(Double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0/9.0 + 273.15;
    }

    public static Double degreesKtoC(Double kelvin) {
        return kelvin - 273.15 ;
    }

    public static Double degreesKtoF(Double kelvin) {
        return (kelvin - 273.15) * 9.0/5.0 + 32;
    }

    LocalDate makeDateFromEpoch(int epoch) {
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDate();
    }



}
