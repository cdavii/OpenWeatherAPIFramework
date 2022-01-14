package org.sparta.utilities;

public class WeatherRecord {
    int code;
    String keyWord;
    String description;
    String icon;

    public WeatherRecord(String details) {
        String[] values = details.split(",");
        code = Integer.parseInt(values[0]);
        keyWord = values[1];
        description = values[2];
        icon = values[3];
    }

    public int getCode() {
        return code;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }


}
