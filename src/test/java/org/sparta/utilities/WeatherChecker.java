package org.sparta.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

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

    public static ArrayList<WeatherRecord> readWeatherTypes() {
        ArrayList<WeatherRecord> weatherTypes = new ArrayList<>();


        File file = new File("src/test/resources/WeatherTypes.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String nextLine = null;
        try {
            assert reader != null;
            nextLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
