package com.myricseptember.weatherapp.ui.weather;

/**
 * Created by Myric on 2017/11/12.
 */

public interface WeatherPresenter {

    void getWeatherByLocation(String appId, String latitude, String longitude, String units);

    void onDestroy();

}
