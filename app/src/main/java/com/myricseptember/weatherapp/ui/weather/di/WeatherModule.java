package com.myricseptember.weatherapp.ui.weather.di;

import android.content.Context;

import com.myricseptember.weatherapp.ui.weather.WeatherInteractor;
import com.myricseptember.weatherapp.ui.weather.WeatherInteractorImpl;
import com.myricseptember.weatherapp.ui.weather.WeatherPresenter;
import com.myricseptember.weatherapp.ui.weather.WeatherPresenterImpl;
import com.myricseptember.weatherapp.ui.weather.WeatherView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Myric on 2017/11/12.
 */
@Module
public class WeatherModule {
    private WeatherView mView;
    private Context mContext;
    private String mBaseUrl;

    public WeatherModule(WeatherView weatherView, String baseUrl, Context context) {

        this.mView = weatherView;
        this.mBaseUrl = baseUrl;
        this.mContext = context;

    }

    @Singleton
    @Provides
    public Context providesContext() {
        return this.mContext;
    }

    @Singleton
    @Provides
    WeatherView providesWeatherView() {
        return this.mView;
    }

    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {

        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl(mBaseUrl)
                .build();
    }

    @Singleton
    @Provides
    public WeatherPresenter providesWeatherPresenter(WeatherView weatherView, WeatherInteractor weatherInteractor, Context context) {

        return new WeatherPresenterImpl(weatherView, weatherInteractor, context);
    }

    @Singleton
    @Provides
    public WeatherInteractor providesWeatherInteractor(Retrofit retrofit) {

        return new WeatherInteractorImpl(retrofit);
    }

}
