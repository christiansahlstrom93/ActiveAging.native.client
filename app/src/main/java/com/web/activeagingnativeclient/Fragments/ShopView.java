package com.web.activeagingnativeclient.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.web.activeagingnativeclient.R;

/**
 * Created by Christian on 2015-09-26.
 */
public class ShopView extends Fragment {

    private View v;
    private static ShopView instance;
    private static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.shop_layout, container, false));
            setInstance(this);
            setmActivity(getActivity());
        }
        return getV();
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public static ShopView getInstance() {
        if (instance == null) {
            instance = new ShopView();
        }
        return instance;
    }

    public static void setInstance(ShopView instance) {
        ShopView.instance = instance;
    }

    public static Activity getmActivity() {
        return mActivity;
    }

    public static void setmActivity(Activity mActivity) {
        ShopView.mActivity = mActivity;
    }
}
