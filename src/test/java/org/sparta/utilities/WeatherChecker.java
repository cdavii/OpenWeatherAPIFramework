package org.sparta.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

public class WeatherChecker {

    public static boolean isValid(int code, String keyWord, String description, String icon) {
        if(keyWord == null || description == null || icon == null) {return false;}

        ArrayList<WeatherRecord> weatherTypes = readWeatherTypes();

        String details = code + ","  + keyWord + ","  + description +  ","  + icon;

        WeatherRecord record = new WeatherRecord(details);

        return isWeatherPresent(weatherTypes, record);

    }

    private static boolean isWeatherPresent(ArrayList<WeatherRecord> weatherTypes, WeatherRecord record) {

        for (WeatherRecord newRec : weatherTypes) {
            if (newRec.getCode() == record.getCode() && newRec.getDescription().equals(record.getDescription()) && newRec.getKeyWord().equals(record.getKeyWord()) && newRec.getIcon().equals(record.getIcon())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to read inputs from a csv file of the accepted weather types for the api
     * @return ArrayList the list of accepted weather types
     */
    public static ArrayList<WeatherRecord> readWeatherTypes() {
        ArrayList<WeatherRecord> weatherTypes = new ArrayList<>();

        File file = new File("src/test/resources/WeatherTypes.csv");
        BufferedReader reader = null;

        //Initialise the  reader
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String nextLine = null;
        //Read the first line
        try {
            assert reader != null;
            nextLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Until EOF, read the next line and add it to the collection
        while(!(nextLine == null)) {
            weatherTypes.add(new WeatherRecord(nextLine));

            try {
                nextLine = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return weatherTypes;
    }

    @Nested
    @DisplayName("Weather Checker Tests")
    public class WeatherCheckerTests{

            @Test
            @DisplayName("Test that a valid weather condition returns true")
            void testThatAValidWeatherConditionReturnsTrue() {
                Assertions.assertTrue(isValid(301,"Drizzle","drizzle","09d"));
            }

            @Test
            @DisplayName("Test that values from different conditions return false")
            void testThatValuesFromDifferentConditionsReturnFalse() {
                Assertions.assertFalse(isValid(200,"Drizzle","drizzle","09d"));
            }
    }
}
