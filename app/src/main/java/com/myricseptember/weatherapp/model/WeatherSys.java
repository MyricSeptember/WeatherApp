package com.myricseptember.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Myric on 2017/11/10.
 */

public class WeatherSys implements Parcelable {

    @SerializedName("sunset")
    private String sunset;
    @SerializedName("sunrise")
    private String sunrise;
    @SerializedName("message")
    private String message;
    @SerializedName("country")
    private String country;

    protected WeatherSys(Parcel in) {

        sunrise = in.readString();
        sunset = in.readString();
        message = in.readString();
        country = in.readString();

    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeatherSys> CREATOR = new Creator<WeatherSys>() {
        @Override
        public WeatherSys createFromParcel(Parcel parcel) {
            return new WeatherSys(parcel);
        }

        @Override
        public WeatherSys[] newArray(int arraySize) {
            return new WeatherSys[arraySize];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sunrise);
        parcel.writeString(sunset);
        parcel.writeString(message);
        parcel.writeString(country);
    }
}
