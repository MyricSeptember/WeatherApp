package com.myricseptember.weatherapp.util;

/**
 * Created by Myric on 2017/11/12.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError();

    void showNoInternet();

    void showNoData();
}
