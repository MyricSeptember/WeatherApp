package com.myricseptember.weatherapp.ui.weather.di;

import com.myricseptember.weatherapp.ui.weather.WeatherFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Myric on 2017/11/12.
 */
@Singleton
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {
    void inject(WeatherFragment target);
}
