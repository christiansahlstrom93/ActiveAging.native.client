
package com.web.activeagingnativeclient.Fragments;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
public class UsernamePasswordView extends Fragment {
    private View v;
    EditText username;
    EditText password;

    Button saveData;

    int fromUserID;
    String fromUserOldPassword;
    String fromUserUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.usernamepassword_view, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            saveData = (Button) v.findViewById(R.id.saveButton);

            username = (EditText) v.findViewById(R.id.usernameEditText);
            username.setGravity(Gravity.LEFT);

            password = (EditText) v.findViewById(R.id.passwordInput);
            password.setGravity(Gravity.LEFT);

            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final String soundValue = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.SOUND);

                    if(soundValue.equals("PÅ")) {
                        MediaPlayer saveSound = MediaPlayer.create(getContext(), R.raw.spara_andringar);
                        saveSound.start();
                    }

                    final HttpURLConnectionExample http = new HttpURLConnectionExample();

                    fromUserID = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.ACCOUNT_KEY, 0); //ID
                    fromUserOldPassword = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.PASS); //OldPassword
                    fromUserUsername = SharedPreferenceHandler.getPublicLibValue(getActivity(), PublicConstants.USER);

                    final String oldPassword = fromUserOldPassword;
                    final String newPassword = password.getText().toString();
                    final String inUsername = username.getText().toString();

                    final boolean hasUppercase = !newPassword.equals(newPassword.toLowerCase());
                    final boolean hasLowercase = !newPassword.equals(newPassword.toUpperCase());
                    final boolean isAtLeast8 = newPassword.length() >= 8;
                    final boolean hasSpecial = !newPassword.matches("[A-Za-z0-9 ]*");
                    final boolean noConditions = !(newPassword.contains("AND") || newPassword.contains("NOT"));

                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {

                            try {
                                if (inUsername.equals(fromUserUsername) && hasLowercase && hasUppercase && isAtLeast8 && hasSpecial && noConditions) {

                                    getActivity().runOnUiThread(new Runnable() {

                                        public void run() {
                                            Toast.makeText(getContext(), "Lösenord ändrat!", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    http.sendPostPassword(oldPassword, newPassword, fromUserID, getActivity());
                                } else {
                                    getActivity().runOnUiThread(new Runnable() {

                                        public void run() {
                                            Toast.makeText(getContext(), "Du har angivit ett felaktigt användarnamn eller ett ogiltigt lösenord. Lösenordet måste innehålla alla tecken, gemener och versaler samt minst åtta tecken ", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
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
