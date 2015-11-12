package com.web.activeagingnativeclient.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.web.activeagingnativeclient.R;

/**
 * Created by Admir on 2015-10-18.
 */
public class UsernamePasswordView extends Fragment {
    private View v;
    EditText username;
    EditText password;

    Button saveData;

    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.usernamepassword_view, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            saveData = (Button) v.findViewById(R.id.saveButton);

            username = (EditText)v.findViewById(R.id.usernameinput);
            username.setGravity(Gravity.LEFT);

            password = (EditText)v.findViewById(R.id.passwordinput);
            password.setGravity(Gravity.LEFT);

            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(alpha);
                    mp = MediaPlayer.create(getContext(), R.raw.click);
                    mp.start();
                    Toast.makeText(getContext(), "Spara!", Toast.LENGTH_LONG).show();
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
