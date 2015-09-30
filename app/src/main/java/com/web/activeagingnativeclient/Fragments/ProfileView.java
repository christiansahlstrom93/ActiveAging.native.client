package com.web.activeagingnativeclient.Fragments;

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
import android.widget.Toast;

import com.web.activeagingnativeclient.R;

/**
 * Created by Admir on 2015-09-27.
 */

public class ProfileView extends Fragment {
    private static View v;
    MediaPlayer mp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getV() == null) {
            setV(inflater.inflate(R.layout.profile_view_layout, container, false));

            mp = MediaPlayer.create(getContext(), R.raw.click);

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            //TODO: Fixa så att namn, adress och ort visas automatiskt efter att de hämtars ur databasen.

            TextView name = (TextView) v.findViewById(R.id.textViewName);
            TextView street = (TextView) v.findViewById(R.id.textViewStreet);
            TextView zipCity = (TextView) v.findViewById(R.id.textViewZipCity);

            final Button historyB = (Button) v.findViewById(R.id.historyButton);
            Button settingsB = (Button) v.findViewById(R.id.settingsButton);
            Button balanceB = (Button) v.findViewById(R.id.balanceButton);
            Button otherB = (Button) v.findViewById(R.id.otherButton);

            historyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(alpha);
                }
            });

            settingsB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(alpha);
                }
            });

            balanceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(alpha);
                }
            });

            otherB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

}
