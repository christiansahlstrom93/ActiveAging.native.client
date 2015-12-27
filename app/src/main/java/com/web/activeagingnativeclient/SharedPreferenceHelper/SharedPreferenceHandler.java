package com.web.activeagingnativeclient.SharedPreferenceHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.web.activeagingnativeclient.Constants.PublicConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Christian on 2015-09-26.
 */
public class SharedPreferenceHandler {

    public static String getPublicLibValue(Context context,String subKey) {
        SharedPreferences prefs = context.getSharedPreferences(
                PublicConstants.PUBLIC_KEY, Context.MODE_PRIVATE);
        return prefs.getString(subKey, null);
    }

    public static String getPublicLibValue(Context context,String subKey,String mainKey) {
        SharedPreferences prefs = context.getSharedPreferences(
                mainKey, Context.MODE_PRIVATE);
        return prefs.getString(subKey, null);
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

    public static void setValueToGlobalLib(Context context, String subKey,String value, String pubKey) {
        SharedPreferences prefs = context.getSharedPreferences(
                pubKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(subKey, value);
        editor.apply();
    }

    public static void removeValue (Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(PublicConstants.PUBLIC_ALERGIC_KEY, Context.MODE_PRIVATE);
        settings.edit().remove(key).commit();
    }

    public static List<String> getAllAllergies (Context context) {

        List<String> stringList = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(
                PublicConstants.PUBLIC_ALERGIC_KEY, Context.MODE_PRIVATE);

        Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.e("loggtag", entry.getKey() + ": " + entry.getValue().toString());
            stringList.add(entry.getValue().toString());
        }

        return stringList;
    }
}
