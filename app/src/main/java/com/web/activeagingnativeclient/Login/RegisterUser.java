package com.web.activeagingnativeclient.Login;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.Fragments.HistoryView;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;
import com.web.activeagingnativeclient.Splash;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ellis on 11/12/15.
 */
public class RegisterUser extends AppCompatActivity {

    EditText userName;
    EditText firstName;
    EditText lastName;
    EditText email_address;
    EditText pass;
    EditText phoneNumber;
    EditText streetName;
    EditText streetNumber;
    EditText city;
    String resp, u, f, l, ea, passe, ph, st, stn, cit;
    String restUrl = "https://activeageing.se/resources/accounts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);
        userName = (EditText) findViewById(R.id.userName);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email_address = (EditText) findViewById(R.id.email_address);
        pass = (EditText) findViewById(R.id.pass);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        streetName = (EditText) findViewById(R.id.streetName);
        streetNumber = (EditText) findViewById(R.id.streetNumber);
        city = (EditText) findViewById(R.id.city);

        Button mRegister_user = (Button) findViewById(R.id.register_user);
        mRegister_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u = userName.getText().toString();
                f = firstName.getText().toString();
                l = lastName.getText().toString();
                ea = email_address.getText().toString();
                passe = pass.getText().toString();
                st = streetName.getText().toString();
                stn = streetNumber.getText().toString();
                cit = city.getText().toString();
                ph = phoneNumber.getText().toString();

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {

                        try {


                            resp = addCustomer(restUrl, u, f, l, ea,
                                    passe,  st,ph, stn, cit);

                            Log.e("loggtag" , "ReSRP " + resp);


                        } catch (Exception ex) {

                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        JSONObject jsonObject = null;
                        try {


                            if (resp != null) {

                                jsonObject = new JSONObject(resp);

                                Log.e("loggtag", "" + jsonObject.getInt("id"));

                                SharedPreferenceHandler.setValueToGlobalLib(RegisterUser.this, PublicConstants.ACCOUNT_KEY, jsonObject.getInt("id"));
                                SharedPreferenceHandler.setValueToGlobalLib(RegisterUser.this, PublicConstants.USER, userName.getText().toString());
                                SharedPreferenceHandler.setValueToGlobalLib(RegisterUser.this, PublicConstants.PASS, pass.getText().toString());
                                SharedPreferenceHandler.setValueToGlobalLib(RegisterUser.this, PublicConstants.CITY, city.getText().toString());
                                SharedPreferenceHandler.setValueToGlobalLib(RegisterUser.this, PublicConstants.ZIP, streetNumber.getText().toString());
                                SharedPreferenceHandler.setValueToGlobalLib(RegisterUser.this, PublicConstants.STREET, streetName.getText().toString());

                                startActivity(new Intent(RegisterUser.this, Splash.class));
                                  finish();
                            }
                        } catch (Exception exc) {

                        }
                    }
                }.execute();

            }
        });
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String addCustomer(String myUrl, String username, String fName, String lName, String
            email, String password,
                              String sName, String pNum, String sNum, String city) {


        String output = "", returnValue = "";

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName",username);
            jsonObject.put("firstName",fName);
            jsonObject.put("lastName",lName);
            jsonObject.put("email",email);
            jsonObject.put("password",password);
            jsonObject.put("phoneNumber",pNum);
            jsonObject.put("streetName",sName);
            jsonObject.put("streetNumber",sNum);
            jsonObject.put("city",city);
            jsonObject.put("zipCodeId",0);

            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(jsonObject.toString().getBytes());
            os.flush();

            Log.e(PublicConstants.TAG, "Code " + conn.getResponseCode());
            if (conn.getResponseCode() != 201) {
                return null;
            }
            Log.e(PublicConstants.TAG, "git?");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                Log.e(PublicConstants.TAG, "Out " + output);

                returnValue = output;
            }

            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return returnValue;
    }

}

