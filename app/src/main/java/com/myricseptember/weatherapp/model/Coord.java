package com.myricseptember.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myricseptember on 2017/11/10.
 */

public class Coord implements Parcelable {

    @SerializedName("lon")
    private String logitude;
    @SerializedName("lat")
    private String latitude;

    protected Coord(Parcel in) {

        logitude = in.readString();
        latitude = in.readString();

    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel parcel) {
            return new Coord(parcel);
        }

        @Override
        public Coord[] newArray(int arraySize) {
            return new Coord[arraySize];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(logitude);
        parcel.writeString(latitude);

    }
}
