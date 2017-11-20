package com.myricseptember.weatherapp.model.response;

import com.google.gson.annotations.SerializedName;
import com.myricseptember.weatherapp.model.Cloud;
import com.myricseptember.weatherapp.model.Coord;
import com.myricseptember.weatherapp.model.WeatherInfo;
import com.myricseptember.weatherapp.model.WeatherItem;
import com.myricseptember.weatherapp.model.WeatherSys;
import com.myricseptember.weatherapp.model.Wind;

import java.util.List;

/**
 * Created by myricseptember on 2017/11/10.
 */

public class WeatherResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("dt")
    private String dateTime;
    @SerializedName("clouds")
    private Cloud clouds;
    @SerializedName("coord")
    private Coord coord;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("cod")
    private String cod;
    @SerializedName("sys")
    private WeatherSys weatherSys;
    @SerializedName("name")
    private String name;
    @SerializedName("base")
    private String base;
    @SerializedName("weather")
    private List<WeatherItem> weather;
    @SerializedName("main")
    private WeatherInfo mainWeatherInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public WeatherSys getWeatherSys() {
        return weatherSys;
    }

    public void setWeatherSys(WeatherSys weatherSys) {
        this.weatherSys = weatherSys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherItem> weather) {
        this.weather = weather;
    }

    public WeatherInfo getMainWeatherInfo() {
        return mainWeatherInfo;
    }

    public void setMainWeatherInfo(WeatherInfo mainWeatherInfo) {
        this.mainWeatherInfo = mainWeatherInfo;
    }
}
