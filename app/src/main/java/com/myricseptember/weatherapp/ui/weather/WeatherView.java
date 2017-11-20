package com.myricseptember.weatherapp.ui.weather;

import com.myricseptember.weatherapp.model.WeatherDisplay;
import com.myricseptember.weatherapp.util.BaseView;

/**
 * Created by Myric on 2017/11/12.
 */

public interface WeatherView extends BaseView {

    void updateData(WeatherDisplay weatherDisplay);

}
