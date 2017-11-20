package com.myricseptember.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myricseptember on 2017/11/10.
 */

public class WeatherItem implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    protected WeatherItem(Parcel in) {

        id = in.readString();
        main = in.readString();
        description = in.readString();
        icon = in.readString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static final Creator<WeatherItem> CREATOR = new Creator<WeatherItem>() {
        @Override
        public WeatherItem createFromParcel(Parcel parcel) {
            return new WeatherItem(parcel);
        }

        @Override
        public WeatherItem[] newArray(int arraySize) {
            return new WeatherItem[arraySize];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(main);
        parcel.writeString(description);
        parcel.writeString(icon);
    }
}
