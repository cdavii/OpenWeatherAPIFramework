package org.sparta;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

public class Injector {
    public static WeatherDTO injectDTO(String URL) {
        WeatherDTO weatherDTO = new WeatherDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            weatherDTO =  objectMapper.readValue(new URL(URL), WeatherDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherDTO;
    }
}
