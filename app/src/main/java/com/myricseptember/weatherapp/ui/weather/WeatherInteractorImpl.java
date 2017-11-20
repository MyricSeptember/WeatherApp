package com.myricseptember.weatherapp.ui.weather;

import com.myricseptember.weatherapp.model.response.WeatherResponse;
import com.myricseptember.weatherapp.network.WeatherRestService;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Myric on 2017/11/12.
 */

public class WeatherInteractorImpl implements WeatherInteractor {

    private Retrofit mRetrofit;
    private static OkHttpClient okHttpClient;

    public WeatherInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }


    static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }

        return okHttpClient;
    }
    @Override
    public Single<WeatherResponse> getWeatherByLocation(String appId, String latitude, String longitude, String units) {
        return mRetrofit.create(WeatherRestService.class).getLocalWeather(appId, latitude, longitude, units);
    }
}
