package com.web.activeagingnativeclient.Fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONObject;


import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;
import com.web.activeagingnativeclient.Splash;



/**
 * Created by Admir on 2015-09-27.
 */
public class ProfileView extends Fragment {
    private static View v;

    String inputName;
    String inputStreet;
    int inputZip;
    String inputCity;

    TextView name;
    TextView street;
    TextView zip;
    TextView city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.profile_view_layout, container, false));

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            name = (TextView) v.findViewById(R.id.textViewName);
            street = (TextView) v.findViewById(R.id.textViewStreet);
            zip = (TextView) v.findViewById(R.id.textViewZip);
            city = (TextView) v.findViewById(R.id.textViewCity);

            //inputData();

            Button startB = (Button) v.findViewById(R.id.startButton);
            Button shopB = (Button) v.findViewById(R.id.shopButton);
            Button historyB = (Button) v.findViewById(R.id.historyButton);
            Button settingsB = (Button) v.findViewById(R.id.settingsButton);

            startB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Splash.pager.setCurrentItem(2);
                    v.startAnimation(alpha);
                }
            });

            shopB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Splash.pager.setCurrentItem(1);
                    v.startAnimation(alpha);

                }
            });

            historyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Splash.pager.setCurrentItem(2);
                    v.startAnimation(alpha);
                }
            });

            settingsB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Splash.pager.setCurrentItem(3);
                    v.startAnimation(alpha);
                }
            });
        }
        return getV();
    }


    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public void inputData() {
        inputName = SharedPreferenceHandler.getPublicLibValue(getActivity(), "userName");
        inputStreet = SharedPreferenceHandler.getPublicLibValue(getActivity(), "userStreet");
        inputZip = SharedPreferenceHandler.getPublicLibValue(getActivity(), "userZip", -1);
        inputCity = SharedPreferenceHandler.getPublicLibValue(getActivity(), "userCity");

        name.setText(inputName);
        street.setText(inputStreet);
        zip.setText(inputZip);
        city.setText(inputCity);
    }
}
