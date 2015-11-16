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

    String fromUserID;

    Button saveData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.personaldata_view, container, false));

            final Animation alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alphastyle);

            saveData = (Button) v.findViewById(R.id.saveButton);

            firstNameEditText = (EditText)v.findViewById(R.id.firstNameEditText);
            firstNameEditText.setGravity(Gravity.LEFT);

            lastNameEditText = (EditText)v.findViewById(R.id.lastNameEditText);
            lastNameEditText.setGravity(Gravity.LEFT);

            streetNameEditText = (EditText)v.findViewById(R.id.streetNameEditText);
            streetNameEditText.setGravity(Gravity.LEFT);

            streetNumberEditText = (EditText)v.findViewById(R.id.streetNumberEditText);
            streetNumberEditText.setGravity(Gravity.LEFT);

            zipCodeEditText = (EditText)v.findViewById(R.id.zipCodeEditText);
            zipCodeEditText.setGravity(Gravity.LEFT);

            cityEditText = (EditText)v.findViewById(R.id.cityEditText);
            cityEditText.setGravity(Gravity.LEFT);

            emailEditText = (EditText)v.findViewById(R.id.emailEditText);
            emailEditText.setGravity(Gravity.LEFT);

            phoneEditText = (EditText)v.findViewById(R.id.phoneEditText);
            phoneEditText.setGravity(Gravity.LEFT);

            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!firstNameEditText.getText().toString().equals("") && !lastNameEditText.getText().toString().equals("") && !streetNameEditText.getText().toString().equals("") && !streetNumberEditText.getText().toString().equals("") && !zipCodeEditText.getText().toString().equals("") && !cityEditText.getText().toString().equals("") && !emailEditText.getText().toString().equals("") && !phoneEditText.getText().toString().equals("")){
                        try {
                            HttpURLConnectionExample http = new HttpURLConnectionExample();

                            fromUserID = SharedPreferenceHandler.getPublicLibValue(getActivity(), "???????"); //ID

                            //String userID = fromUserID;
                            String userID = "1";

                            //GET information from account by using an ID.
                            http.sendGet(userID);

                            //Put every income information in a variable.
                            String firstName = http.firstNameMethod();
                            String lastName = http.lastNameMethod();
                            String streetName = http.streetNameMethod();
                            String streetNumber = http.streetNumberMethod();
                            String city = http.cityMethod();
                            String email = http.emailMethod();
                            String phoneNumber = http.phoneNumberMethod();

                            //Print them out
                            //System.out.println("Namn: " + firstName + " LastName: " + lastName + " StreetName: " + streetName + " StreetNumber: " + streetNumber +" City: " + city + " Email: " + email + " PhoneNumber: " + phoneNumber);

                            //Get all the values from EditText fields.
                            String myFirstName = firstNameEditText.getText().toString();
                            String myLastName = lastNameEditText.getText().toString();
                            String myStreetName = streetNameEditText.getText().toString();
                            String myStreetNumber = streetNumberEditText.getText().toString();
                            String myZipCode = zipCodeEditText.getText().toString();
                            String myCity = cityEditText.getText().toString();
                            String myEmail = emailEditText.getText().toString();
                            String myPhone = phoneEditText.getText().toString();

                            System.out.println("First print after input!" + "FirstName " + myFirstName + " LastName: " + myLastName + "Resten samlat: " + myStreetName + myStreetNumber + myZipCode + myCity + myPhone + myEmail);

                            //Invoke the sendPostUserData method and pass all the variables as in paremeters.
                            http.sendPostUserData(myFirstName, myLastName, myStreetName, myStreetNumber, myZipCode, myCity, myEmail, myPhone);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        v.startAnimation(alpha);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();

                        ft.replace(R.id.settingsID, new SettingsView(), "NewFragmentTag");
                        ft.commit();
                    }

                    else {
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
