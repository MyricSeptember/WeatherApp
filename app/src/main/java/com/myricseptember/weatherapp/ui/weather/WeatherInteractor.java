package com.myricseptember.weatherapp.ui.weather;

import com.myricseptember.weatherapp.model.response.WeatherResponse;

import io.reactivex.Single;

/**
 * Created by Myric on 2017/11/12.
 */

public interface WeatherInteractor {
    Single<WeatherResponse> getWeatherByLocation(String appId, String latitude, String longitude, String units);

}
