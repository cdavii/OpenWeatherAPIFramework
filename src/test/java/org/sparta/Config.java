package org.sparta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String fileLocation = "src/test/resources/config.properties";
    private static final Properties config;

    static {
        config = new Properties();
        try {
            config.load(new BufferedReader(new FileReader(fileLocation)));
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static String getApiKey(){
        return config.getProperty("API_KEY");
    }
}
