package com.web.activeagingnativeclient.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class PersonalDataView extends Fragment {
    private View v;
    EditText name;
    EditText lastname;
    EditText address;
    EditText phone;
    EditText email;

    Button saveData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.personaldata_view, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            saveData = (Button) v.findViewById(R.id.saveButton);

            name = (EditText)v.findViewById(R.id.inputName);
            name.setGravity(Gravity.LEFT);

            lastname = (EditText)v.findViewById(R.id.inputLastname);
            lastname.setGravity(Gravity.LEFT);

            address = (EditText)v.findViewById(R.id.inputAddress);
            address.setGravity(Gravity.LEFT);

            phone = (EditText)v.findViewById(R.id.inputPhone);
            phone.setGravity(Gravity.LEFT);

            email = (EditText)v.findViewById(R.id.inputEmail);
            email.setGravity(Gravity.LEFT);

            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(alpha);

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
