package com.web.activeagingnativeclient.Fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.web.activeagingnativeclient.R;

/**
 * Created by Christian on 2015-10-02.
 */
public class SettingsView extends Fragment {
    Button usernamepasswordVar;
    Button personalDataVar;

    private View v;

    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.settings_view, container, false));

            usernamepasswordVar = (Button) v.findViewById(R.id.usernamepassword);
            personalDataVar = (Button) v.findViewById(R.id.personaldata);

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            usernamepasswordVar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(getContext(), R.raw.myclick);
                    mp.start();
                    v.startAnimation(alpha);

                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.settingsID, new UsernamePasswordView(), "NewFragmentTag");
                    ft.commit();
                }
            });


            personalDataVar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(getContext(), R.raw.myclick);
                    mp.start();
                    v.startAnimation(alpha);

                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.settingsID, new PersonalDataView(), "NewFragmentTag");
                    ft.commit();
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
