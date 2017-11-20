package com.myricseptember.weatherapp.network;

import com.myricseptember.weatherapp.model.response.WeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Myric on 2017/11/10.
 */

public interface WeatherRestService {

    @GET("weather")
    Single<WeatherResponse> getLocalWeather(@Query("appid") String appId,
                                            @Query("lat") String latitude,
                                            @Query("lon") String logitude,
                                            @Query("units") String unit);
}
