package com.web.activeagingnativeclient.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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


import com.web.activeagingnativeclient.DatabaseCommunication.HttpURLConnectionExample;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;

/**
 * Created by Admir on 2015-10-18.
 */
public class UsernamePasswordView extends Fragment {
    private View v;
    EditText username;
    EditText password;

    Button saveData;

    String fromUserID;
    String fromUserOldPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.usernamepassword_view, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            saveData = (Button) v.findViewById(R.id.saveButton);

            username = (EditText) v.findViewById(R.id.firstNameEditText);
            username.setGravity(Gravity.LEFT);

            password = (EditText) v.findViewById(R.id.passwordInput);
            password.setGravity(Gravity.LEFT);

            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                        try {
                            HttpURLConnectionExample http = new HttpURLConnectionExample();

                            fromUserID = SharedPreferenceHandler.getPublicLibValue(getActivity(), "???????"); //ID
                            fromUserOldPassword = SharedPreferenceHandler.getPublicLibValue(getActivity(), "???????"); //OldPassword

                            String userID = fromUserID;
                            String oldPassword = fromUserOldPassword;
                            String newPassword = password.getText().toString();

                            //GET information from account by using an ID.
                            http.sendGet(userID);

                            //Put every income information in a variable.
                            String type = http.typeMethod();
                            String city = http.cityMethod();
                            String created = http.createdMethod();
                            String email = http.emailMethod();
                            String firstName = http.firstNameMethod();
                            String id = http.idMethod();
                            String lastName = http.lastNameMethod();
                            String phoneNumber = http.phoneNumberMethod();
                            String role = http.roleMethod();
                            String streetName = http.streetNameMethod();
                            String streetNumber = http.streetNumberMethod();
                            String updated = http.updatedMethod();
                            String userName = http.usernameMethod();

                            System.out.println("UserName: " + userName + "Updated: " + updated + "Role: " + role + "PhoneNumber: " + phoneNumber + "ID: " + id + "Type: " + type + "City: " + city + "Created: " + created + "FirstName: " + firstName + " LastName: " + lastName + " StreetName: " + streetName + " StreetNumber: " + streetNumber +" City: " + city + " Email: " + email);

                            System.out.println("Skriva ut input: " + "Namn: " + username + " Lösenord: " + password);

                            /* Hårdkod!
                            String myOldPassword = "emil";
                            String myNewPassword = "norsken";

                            http.sendPostPassword(myOldPassword, myNewPassword, userID);*/

                            http.sendPostPassword(oldPassword, newPassword, userID);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        v.startAnimation(alpha);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.settingsID, new SettingsView(), "NewFragmentTag");
                        ft.commit();
                    } else {
                        Toast.makeText(getContext(), "Det finns tomma fält. Se till att alla fält är ifyllda.", Toast.LENGTH_LONG).show();
                    }
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
