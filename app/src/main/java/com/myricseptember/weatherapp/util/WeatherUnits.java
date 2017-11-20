package com.myricseptember.weatherapp.util;

/**
 * Created by Myric on 2017/11/12.
 */

public enum WeatherUnits {

    IMPERIAL("imperial"), METRIC("metric");
    private String value;

    WeatherUnits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
