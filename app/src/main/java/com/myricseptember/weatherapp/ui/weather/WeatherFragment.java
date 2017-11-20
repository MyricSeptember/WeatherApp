package com.myricseptember.weatherapp.ui.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.myricseptember.weatherapp.R;
import com.myricseptember.weatherapp.model.WeatherDisplay;
import com.myricseptember.weatherapp.ui.weather.di.DaggerWeatherComponent;
import com.myricseptember.weatherapp.ui.weather.di.WeatherModule;
import com.myricseptember.weatherapp.util.Constants;
import com.myricseptember.weatherapp.util.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Myric on 2017/11/12.
 */

public class WeatherFragment extends Fragment implements WeatherView {
    private static final String ARG_LAT = "arg_lat";
    private static final String ARG_LON = "arg_lon";
    public static String TAG = "fragment_current_weather";
    @Inject
    WeatherPresenter mWeatherPresenter;
    @BindView(R.id.txtDate)
    TextView mCurrentDate;
    @BindView(R.id.txtLocation)
    TextView mCurrentLocation;
    @BindView(R.id.txtMaxTemp)
    TextView mMaxTemp;
    @BindView(R.id.txtMinTemp)
    TextView mMinTemp;
    @BindView(R.id.txtdescription)
    TextView mDescription;
    @BindView(R.id.txtMaxText)
    TextView mMaxText;
    @BindView(R.id.txtMinText)
    TextView mMinText;
    @BindView(R.id.imgIcon)
    ImageView mWeatherImage;
    @BindView(R.id.prfr_location)
    ProgressBar progressBar;
    @BindView(R.id.lnr_current_weather)
    ConstraintLayout currentLinear;

    private String mLatitude;
    private String mLongitude;


    public WeatherFragment() {
    }


    public static WeatherFragment newInstance(String latitude, String longitude) {

        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LAT, latitude);
        args.putString(ARG_LON, longitude);
        weatherFragment.setArguments(args);
        return weatherFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mLatitude = getArguments().getString(ARG_LAT);
            mLongitude = getArguments().getString(ARG_LON);
        }
        DaggerWeatherComponent.builder().weatherModule(new WeatherModule(this, Constants.BASE_URL, getContext())).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_weather, container, false);
        ButterKnife.bind(this, view);

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWeatherPresenter.getWeatherByLocation(Constants.OPEN_WEATHER_KEY, mLatitude, mLongitude, Constants.WEATHER_UNITS.getValue());
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        makeToast(R.string.general_error);
    }

    @Override
    public void showNoInternet() {
        makeToast(R.string.no_internet_message);
    }

    @Override
    public void showNoData() {
        makeToast(R.string.there_is_no_data_to_display);
    }

    @Override
    public void updateData(WeatherDisplay weatherDisplay) {
        mMaxText.setVisibility(View.VISIBLE);
        mCurrentDate.setVisibility(View.VISIBLE);
        mMaxTemp.setVisibility(View.VISIBLE);
        mMinTemp.setVisibility(View.VISIBLE);
        mCurrentLocation.setVisibility(View.VISIBLE);
        mCurrentDate.setVisibility(View.VISIBLE);
        mDescription.setVisibility(View.VISIBLE);
        mWeatherImage.setVisibility(View.VISIBLE);
        mMinText.setVisibility(View.VISIBLE);
        currentLinear.setVisibility(View.VISIBLE);

        mMaxTemp.setText(weatherDisplay.getMaxTemp() + " " + getString(R.string.celsius));
        mMinTemp.setText(weatherDisplay.getMinTemp() + " " + getString(R.string.celsius));
        mCurrentLocation.setText(weatherDisplay.getLocation());
        mCurrentDate.setText(weatherDisplay.getDate());
        mDescription.setText(weatherDisplay.getDescription());
        ImageLoader.getImageByDescription(weatherDisplay.getDescription(), mWeatherImage);
    }

    private void makeToast(@StringRes int message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
