package com.web.activeagingnativeclient.CommonHelpers;

import android.content.Context;
import android.util.Log;

import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;

/**
 * Created by Christian on 2015-12-12.
 */
public class Filter {

    public boolean hasAllergies (Context c,String tag) {

        tag = tag.toLowerCase();

        for (int i = 0; i < SharedPreferenceHandler.getAllAllergies(c).size(); i++) {
            if (tag.contains(SharedPreferenceHandler.getAllAllergies(c).get(i).toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
