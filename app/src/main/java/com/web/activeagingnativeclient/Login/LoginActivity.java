package com.web.activeagingnativeclient.Login;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;
import com.web.activeagingnativeclient.Splash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {


    private UserLoginTask mAuthTask = null;

    private EditText user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);

    }

    public void login(View v) {
        try {
            mAuthTask = new UserLoginTask(user.getText().toString(), pass.getText().toString());
            mAuthTask.execute();
        } catch (Exception e) {
            Toast.makeText(this, "Fyll i korrekt", Toast.LENGTH_LONG).show();
        }
    }

    public void register(View v) {
        startActivity(new Intent(this, RegisterUser.class));
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private String name, json = "";
        private final String mPassword;
        int id;

        UserLoginTask(String name, String password) {
            this.name = name;
            mPassword = password;
            json = "{\n" +
                    "  \"userName\": \"" + this.name + "\",\n" +
                    "  \"password\": \"" + mPassword + "\",\n" +
                    "  \"email\": \"string\"\n" +
                    "}";
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                json = auth("https://activeageing.se/resources/authentication", json);
                JSONObject jsonObject = new JSONObject(json);
                id = jsonObject.getInt("id");

                String acc = getResponseBody("https://activeageing.se/resources/accounts/"+id);
                jsonObject = new JSONObject(acc);

                SharedPreferenceHandler.setValueToGlobalLib(LoginActivity.this, PublicConstants.CITY, jsonObject.getString("city"));
                SharedPreferenceHandler.setValueToGlobalLib(LoginActivity.this, PublicConstants.ZIP,jsonObject.getString("streetNumber"));
                SharedPreferenceHandler.setValueToGlobalLib(LoginActivity.this, PublicConstants.STREET, jsonObject.getString("streetName"));


                Log.e("loggtag", "CITY" +jsonObject.getString("streetNumber") + "  " + jsonObject.getString("streetName"));
            } catch (Exception e) {
                Log.e("loggtag",e+"");
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {



                try {


                    JSONObject jsonObject = new JSONObject(json);
                    Log.e("loggtag", jsonObject + "  JSON");

                    if (jsonObject.getInt("id") > 0) {
                        SharedPreferenceHandler.setValueToGlobalLib(LoginActivity.this, PublicConstants.ACCOUNT_KEY, id);
                        SharedPreferenceHandler.setValueToGlobalLib(LoginActivity.this, PublicConstants.USER, name);
                        SharedPreferenceHandler.setValueToGlobalLib(LoginActivity.this, PublicConstants.PASS, mPassword);

                        startActivity(new Intent(LoginActivity.this, Splash.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,"Fel lösenord eller användarnamn",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                }



        }

        public String auth(String myUrl, String params) {

            String output = "", returnValue = "";

            try {

                URL url = new URL(myUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                OutputStream os = conn.getOutputStream();
                os.write(params.getBytes());
                os.flush();

                Log.e(PublicConstants.TAG, "Code " + conn.getResponseCode());
                if (conn.getResponseCode() != 200) {
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

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return returnValue;
        }

    }

    public String getResponseBody(String murl) {
        try {

            URL url = new URL(murl);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) httpCon.getContent()));

            String readLine;
            String responseBody = "";
            while (((readLine = br.readLine()) != null)) {
                responseBody += "\n" + readLine;
            }

            return responseBody;
        } catch (Exception e) {
            Log.e(PublicConstants.TAG, "Error i getResponse " + e);
        }
        return null;
    }

}
