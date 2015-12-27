package com.web.activeagingnativeclient.Fragments;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.Server.HttpURLConnectionExample;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;

/**
 * Created by Admir on 2015-10-18.
 */
public class PersonalDataView extends Fragment {
    private View v;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText streetNameEditText;
    EditText streetNumberEditText;
    EditText zipCodeEditText;
    EditText cityEditText;
    EditText emailEditText;
    EditText phoneEditText;

    int fromUserID;

    Button saveData;

    String street;
    String city;
    String number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.personaldata_view, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            saveData = (Button) v.findViewById(R.id.saveButton);

            firstNameEditText = (EditText) v.findViewById(R.id.firstNameEditText);
            firstNameEditText.setGravity(Gravity.LEFT);

            lastNameEditText = (EditText) v.findViewById(R.id.lastNameEditText);
            lastNameEditText.setGravity(Gravity.LEFT);

            streetNameEditText = (EditText) v.findViewById(R.id.streetNameEditText);
            streetNameEditText.setGravity(Gravity.LEFT);

            streetNumberEditText = (EditText) v.findViewById(R.id.streetNumberEditText);
            streetNumberEditText.setGravity(Gravity.LEFT);

            zipCodeEditText = (EditText) v.findViewById(R.id.zipCodeEditText);
            zipCodeEditText.setGravity(Gravity.LEFT);

            cityEditText = (EditText) v.findViewById(R.id.cityEditText);
            cityEditText.setGravity(Gravity.LEFT);

            emailEditText = (EditText) v.findViewById(R.id.emailEditText);
            emailEditText.setGravity(Gravity.LEFT);

            phoneEditText = (EditText) v.findViewById(R.id.phoneEditText);
            phoneEditText.setGravity(Gravity.LEFT);

            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String soundValue = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.SOUND);

                    if(soundValue.equals("PÅ")) {
                        MediaPlayer saveSound = MediaPlayer.create(getContext(), R.raw.spara_andringar);
                        saveSound.start();
                    }

                    if (!firstNameEditText.getText().toString().equals("") && !lastNameEditText.getText().toString().equals("") && !streetNameEditText.getText().toString().equals("") && !streetNumberEditText.getText().toString().equals("") && !zipCodeEditText.getText().toString().equals("") && !cityEditText.getText().toString().equals("") && !emailEditText.getText().toString().equals("") && !phoneEditText.getText().toString().equals("")) {
                        try {

                            fromUserID = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.ACCOUNT_KEY, 0); //ID

                            final String myFirstName = firstNameEditText.getText().toString();
                            final String myLastName = lastNameEditText.getText().toString();
                            final String myStreetName = streetNameEditText.getText().toString();
                            final String myStreetNumber = streetNumberEditText.getText().toString();
                            final String myZipCode = zipCodeEditText.getText().toString();
                            final String myCity = cityEditText.getText().toString();
                            final String myEmail = emailEditText.getText().toString();
                            final String myPhone = phoneEditText.getText().toString();

                            street = myStreetName;
                            city = myCity;
                            number = myStreetNumber;

                            SharedPreferenceHandler.setValueToGlobalLib(getContext(), PublicConstants.STREET, street);
                            SharedPreferenceHandler.setValueToGlobalLib(getContext(), PublicConstants.CITY, city);
                            SharedPreferenceHandler.setValueToGlobalLib(getContext(), PublicConstants.ZIP, number);

                            new AsyncTask<Void, Void, Void>() {

                                @Override
                                protected Void doInBackground(Void... params) {
                                    try {
                                        final HttpURLConnectionExample http = new HttpURLConnectionExample();

                                        http.sendPostUserData(myFirstName, myLastName, myStreetName, myStreetNumber, myZipCode, myCity, myEmail, myPhone, fromUserID);
                                    } catch (Exception e) {
                                        Log.e(PublicConstants.TAG, "Error i första try " + e);
                                    }

                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    super.onPostExecute(aVoid);
                                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.settingsID, new SettingsView(), "NewFragmentTag");
                                    ft.commit();

                                }
                            }.execute();


                        } catch (Exception e) {
                            Log.e(PublicConstants.TAG, "Fel " + e);
                        }
                    } else {
                        Toast.makeText(getContext(), "Det finns tomma fält. Se till att alla fält är ifyllda.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        return getV();
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.settingsID, new SettingsView(), "NewFragmentTag");
                    ft.commit();
                    return true;
                }
                return false;
            }
        });
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }
}
