package com.myricseptember.weatherapp.ui.weather;

import android.content.Context;

import com.myricseptember.weatherapp.model.WeatherDisplay;
import com.myricseptember.weatherapp.model.response.WeatherResponse;
import com.myricseptember.weatherapp.util.NetworkStatusHelper;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Myric on 2017/11/12.
 */

public class WeatherPresenterImpl implements WeatherPresenter {


    private static final String TIME_FORMAT = "EEE, d MMM yyyy";
    private static final String EMPTY_DEFAULT_VALUE_TEXT = "-";
    private WeatherView mView;
    private WeatherInteractor mInteractor;
    private Disposable mRequestDisposible;
    private WeakReference<Context> mContextWeakReference;
    private SimpleDateFormat mSimpleDateFormat;
    private Calendar mCalendar;

    public WeatherPresenterImpl(WeatherView view, WeatherInteractor weatherInteractor, Context context) {

        this.mView = view;
        this.mInteractor = weatherInteractor;
        mContextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public void getWeatherByLocation(String appId, String latitude, String longitude, String units) {
        requestDispose();

        if (!NetworkStatusHelper.checkIfInternetIsAvailable(mContextWeakReference.get())) {

            mView.showNoInternet();
            return;

        }
        mView.showLoading();
        mRequestDisposible = mInteractor.getWeatherByLocation(appId, latitude, longitude, units).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResponse>() {
                               @Override
                               public void accept(@NonNull WeatherResponse weatherResponse) throws Exception {

                                   mView.hideLoading();
                                   if (weatherResponse != null) {

                                       prepareData(weatherResponse);

                                   } else {

                                       mView.showNoData();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.hideLoading();
                                   mView.showError();
                               }
                           }

                );
    }

    private void prepareData(WeatherResponse weatherResponse) {

        WeatherDisplay weatherDisplay = new WeatherDisplay();
        //TODO COMPLETE THIS BASED ON WHAT INFO I WANT TO DISPLAY

        if (weatherResponse.getMainWeatherInfo() != null) {
            weatherDisplay.setMaxTemp(weatherResponse.getMainWeatherInfo().getTempMax());
            weatherDisplay.setMinTemp(weatherResponse.getMainWeatherInfo().getTempMax());
            weatherDisplay.setDescription(weatherResponse.getWeather().get(0).getDescription());
            weatherDisplay.setIcon(weatherResponse.getWeather().get(0).getIcon());
            weatherDisplay.setLocation(weatherResponse.getName());


        } else {

            weatherDisplay.setMaxTemp(EMPTY_DEFAULT_VALUE_TEXT);
            weatherDisplay.setMinTemp(EMPTY_DEFAULT_VALUE_TEXT);
        }

        if (weatherResponse.getWeather() != null && weatherResponse.getWeather().size() > 0) {

            weatherDisplay.setDescription(weatherResponse.getWeather().get(0).getDescription());
            weatherDisplay.setIcon(weatherResponse.getWeather().get(0).getIcon());
            weatherDisplay.setLocation(weatherResponse.getName() + ", " + weatherResponse.getWeatherSys().getCountry());
            weatherDisplay.setDate(prepareDate());

        } else {

            weatherDisplay.setDescription(EMPTY_DEFAULT_VALUE_TEXT);
            weatherDisplay.setIcon(EMPTY_DEFAULT_VALUE_TEXT);

        }
        mView.updateData(weatherDisplay);

    }


    //TODO PREPARE THE DATE
    @Override
    public void onDestroy() {

        requestDispose();
    }

    private String prepareDate() {

        mCalendar = Calendar.getInstance();
        mSimpleDateFormat = new SimpleDateFormat(TIME_FORMAT);

        String formattedDate = mSimpleDateFormat.format(mCalendar.getTime());

        return formattedDate;
    }

    private void requestDispose() {

        if (mRequestDisposible != null && !mRequestDisposible.isDisposed()) {
            mRequestDisposible.dispose();

        }
    }
}
