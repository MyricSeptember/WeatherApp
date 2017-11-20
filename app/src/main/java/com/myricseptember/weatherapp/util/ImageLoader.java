package com.myricseptember.weatherapp.util;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myricseptember.weatherapp.R;

/**
 * Created by Myric on 2017/11/12.
 */

public class ImageLoader {

    public static void loadImage(@DrawableRes int drawablId, ImageView imageView) {

        Glide.with(imageView.getContext()).load(drawablId).asBitmap().into(imageView);
    }

    public static void getImageByDescription(String description, ImageView imageView) {

        switch (description) {

            case "clear sky":
                loadImage(R.drawable.sun_01, imageView);
                break;
            case "few clouds":
                loadImage(R.drawable.clouds_01, imageView);
                break;

            case "scattered clouds":
                loadImage(R.drawable.clouds_01, imageView);
                break;

            case "broken clouds":
                loadImage(R.drawable.clouds_01, imageView);
                break;

            case "shower rain":
                loadImage(R.drawable.clouds_with_rain_01, imageView);
                break;

            case "rain":
                loadImage(R.drawable.clouds_with_rain_01, imageView);
                break;

            case "thunderstorm":
                loadImage(R.drawable.clouds_with_lighting_littlerain_01, imageView);
                break;

            case "snow":
                loadImage(R.drawable.clouds_with_snow_01, imageView);
                break;


            case "mist":
                loadImage(R.drawable.clouds_with_snow_01, imageView);
                break;

            case "overcast clouds":
                loadImage(R.drawable.clouds_01, imageView);
                break;

        }

    }
}
