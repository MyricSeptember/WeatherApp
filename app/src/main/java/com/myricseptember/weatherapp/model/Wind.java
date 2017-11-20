package com.myricseptember.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Myric on 2017/11/10.
 */

public class Wind implements Parcelable {

    @SerializedName("speed")
    private String speed;
    @SerializedName("deg")
    private String degree;

    protected Wind(Parcel in) {

        speed = in.readString();
        degree = in.readString();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel parcel) {
            return new Wind(parcel);
        }

        @Override
        public Wind[] newArray(int arraySize) {
            return new Wind[arraySize];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(speed);
        parcel.writeString(degree);
    }
}
