package org.sparta.tests.NonFrameworkTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.Config;
import org.sparta.dtos.WeatherDTO;
import org.sparta.utilities.WeatherRecord;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;

public class WeatherTests {
    private final static String URL = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=" + Config.getApiKey();
    private static HttpResponse<String> connectionResponse;
    private static ArrayList<WeatherRecord> weatherCodes = new ArrayList<>();
    private static WeatherDTO weatherDTOTest;

    private static HttpResponse<String> getConnectionResponse() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static WeatherDTO injectDTO(String URL) {
        WeatherDTO weatherDTO = new WeatherDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            weatherDTO =  objectMapper.readValue(new URL(URL), WeatherDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherDTO;
    }

    private static void readWeatherCodesFromCsv(String fileName) {
        try  {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String lineOfData = bufferedReader.readLine();
            while (lineOfData != null) {
                WeatherRecord weatherRecords = new WeatherRecord(lineOfData);
                weatherCodes.add(weatherRecords);
                lineOfData = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @BeforeEach
    void setup(){
       connectionResponse = getConnectionResponse();
       readWeatherCodesFromCsv(Config.getWeatherCodes());
       weatherDTOTest = injectDTO(URL);
    }

    @Test
    @DisplayName("Check weather code id corresponds to the right weather type from CSV file")
    void checkWeatherCodeIdCorrespondsToTheRightWeatherTypeFromCsvFile() {
        int weatherCodeCheck = 0;
        Iterator<WeatherRecord> itr = weatherCodes.iterator();
        while (itr.hasNext()) {
            if (weatherDTOTest.getWeather().get(0).getId() == itr.next().getCode()) {
                weatherCodeCheck = weatherDTOTest.getWeather().get(0).getId() ;
            }
        }
        Assertions.assertEquals(weatherDTOTest.getWeather().get(0).getId(), weatherCodeCheck);
    }

    @Test
    @DisplayName("Check weather code description matches with existing one in CSV file")
    void checkWeatherCodeDescriptionMatchesWithExistingOneInCsvFile() {
        String weatherDescriptionCheck = "";
        Iterator<WeatherRecord> itr = weatherCodes.iterator();
        while (itr.hasNext()) {
            if (weatherDTOTest.getWeather().get(0).getId() == itr.next().getCode()) {
                weatherDescriptionCheck= weatherDTOTest.getWeather().get(0).getDescription() ;
            }
        }
        Assertions.assertEquals(weatherDTOTest.getWeather().get(0).getDescription(), weatherDescriptionCheck);
    }
}

