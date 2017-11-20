package com.myricseptember.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myricseptember on 2017/11/10.
 */

public class Cloud implements Parcelable {


    @SerializedName("all")
    private String all;

    protected Cloud(Parcel in) {
        all = in.readString();
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new Cloud(parcel);
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
        parcel.writeString(all);
    }
}
