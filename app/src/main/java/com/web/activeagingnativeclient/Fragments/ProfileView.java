package com.web.activeagingnativeclient.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;
import com.web.activeagingnativeclient.Splash;

/**
 * Created by Admir on 2015-09-27.
 */

public class ProfileView extends Fragment {
    private static View v;
    private static Activity activity;

    String inputName;
    String inputStreet;
    String inputZip;
    String inputCity;

    TextView name;
    TextView street;
    TextView zip;
    TextView city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getV() == null) {
            activity = getActivity();
            setV(inflater.inflate(R.layout.profile_view_layout, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            String yes = "PÅ";
                            SharedPreferenceHandler.setValueToGlobalLib(getContext(), PublicConstants.SOUND, yes);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            String no = "AV";
                            SharedPreferenceHandler.setValueToGlobalLib(getContext(), PublicConstants.SOUND, no);
                            break;
                    }
                }
            };

            final String soundValue = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.SOUND);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Just nu är ditt ljud: " + soundValue + ". Välj om det ska vara på eller av. Starta om applikationen efter val för att ädringen ska gå igenom.").setPositiveButton("På", dialogClickListener)
                    .setNegativeButton("Av", dialogClickListener).show();

            name = (TextView) v.findViewById(R.id.textViewName);
            street = (TextView) v.findViewById(R.id.textViewStreet);
            zip = (TextView) v.findViewById(R.id.textViewZip);
            city = (TextView) v.findViewById(R.id.textViewCity);

            inputData();

            Button startB = (Button) v.findViewById(R.id.startButton);
            Button shopB = (Button) v.findViewById(R.id.shopButton);
            Button historyB = (Button) v.findViewById(R.id.historyButton);
            Button settingsB = (Button) v.findViewById(R.id.settingsButton);

            startB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(soundValue.equals("PÅ")) {
                        MediaPlayer saveSound = MediaPlayer.create(activity, R.raw.startsida);
                        saveSound.start();
                    }

                    v.startAnimation(alpha);
                }
            });

            shopB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(soundValue.equals("PÅ")) {
                        MediaPlayer saveSound = MediaPlayer.create(activity, R.raw.bestalla);
                        saveSound.start();
                    }

                    Splash.pager.setCurrentItem(1);
                    v.startAnimation(alpha);

                }
            });

            historyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(soundValue.equals("PÅ")) {
                        MediaPlayer saveSound = MediaPlayer.create(activity, R.raw.historik);
                        saveSound.start();
                    }

                    Splash.pager.setCurrentItem(2);
                    v.startAnimation(alpha);
                }
            });

            settingsB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(soundValue.equals("PÅ")) {
                        MediaPlayer saveSound = MediaPlayer.create(activity, R.raw.installningar);
                        saveSound.start();
                    }

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
        inputName = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.USER);
        inputStreet = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.STREET);
        inputZip = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.ZIP);
        inputCity = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.CITY);

        name.setText(inputName);
        street.setText(inputStreet);
        zip.setText(inputZip);
        city.setText(inputCity);
    }
}
