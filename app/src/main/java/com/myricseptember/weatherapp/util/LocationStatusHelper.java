package com.myricseptember.weatherapp.util;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by Myric on 2017/11/12.
 */

public class LocationStatusHelper {

    public static boolean checkIfLocationIsEnabled(Context mContext){
        int locationMode= Settings.Secure.getInt(
                mContext.getContentResolver(),
                Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_OFF
        );

        if(locationMode == Settings.Secure.LOCATION_MODE_OFF){

            return false;
        }
        return true;
    }
}
