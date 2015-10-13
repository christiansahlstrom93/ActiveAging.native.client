package com.web.activeagingnativeclient.SharedPreferenceHelper;

import android.content.Context;
import android.content.SharedPreferences;

import com.web.activeagingnativeclient.Constants.PublicConstants;

/**
 * Created by Christian on 2015-09-26.
 */
public class SharedPreferenceHandler {

    public static String getPublicLibValue(Context context,String subKey) {
        SharedPreferences prefs = context.getSharedPreferences(
                PublicConstants.PUBLIC_KEY, Context.MODE_PRIVATE);
        return prefs.getString(subKey, "Nisse Olofsson");//TODO ta bort namnet
    }

    public static int getPublicLibValue(Context context,String subKey,int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(
                PublicConstants.PUBLIC_KEY, Context.MODE_PRIVATE);
        return prefs.getInt(subKey, defaultValue);
    }

    public static void setValueToGlobalLib(Context context, String subKey,String value) {
        SharedPreferences prefs = context.getSharedPreferences(
                PublicConstants.PUBLIC_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(subKey, value);
        editor.apply();
    }

    public static void setValueToGlobalLib(Context context, String subKey,int value) {
        SharedPreferences prefs = context.getSharedPreferences(
                PublicConstants.PUBLIC_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(subKey, value);
        editor.apply();
    }
}
