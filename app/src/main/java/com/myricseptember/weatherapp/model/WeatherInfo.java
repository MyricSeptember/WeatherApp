package com.myricseptember.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myricseptember on 2017/11/10.
 */

public class WeatherInfo implements Parcelable {

    @SerializedName("temp")
    private String temp;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("temp_max")
    private String tempMax;
    @SerializedName("temp_min")
    private String tempMin;
    @SerializedName("sea_level")
    private String seaLevel;
    @SerializedName("grnd_level")
    private String groundLevel;

    protected WeatherInfo(Parcel in) {

        temp = in.readString();
        pressure = in.readString();
        humidity = in.readString();
        tempMax = in.readString();
        tempMin = in.readString();
        seaLevel = in.readString();
        groundLevel = in.readString();

    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(String seaLevel) {
        this.seaLevel = seaLevel;
    }

    public String getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(String groundLevel) {
        this.groundLevel = groundLevel;
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new WeatherInfo(parcel);
        }

        @Override
        public Object[] newArray(int arraySize) {
            return new Object[arraySize];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(temp);
        parcel.writeString(pressure);
        parcel.writeString(humidity);
        parcel.writeString(tempMax);
        parcel.writeString(tempMin);
        parcel.writeString(seaLevel);
        parcel.writeString(groundLevel);
    }
}
