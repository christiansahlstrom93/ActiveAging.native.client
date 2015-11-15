package com.web.activeagingnativeclient.Login;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
                System.out.println("sasa!");

                Log.e("hej", addCustomer(restUrl, userName.getText().toString(),firstName.getText().toString(),lastName.getText().toString(), email_address.getText().toString(),
                        pass.getText().toString(), phoneNumber.getText().toString(),streetName.getText().toString(),streetNumber.getText().toString(), city.getText().toString() ));

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String addCustomer(String myUrl, String username, String fName, String lName, String email, String password,
                              String sName, String pNum, String sNum, String city) {
        String params = "{\n"
                + "  \"userName\": \"" + username + "\",\n"
                + "  \"firstName\": \"" + fName + "\",\n"
                + "  \"lastName\": \"" + lName + "\",\n"
                + "  \"email\": \"" + email + "\",\n"
                + "  \"password\": \"" + password + "\",\n"
                + "  \"phoneNumber\": \"" + pNum + "\",\n"
                + "  \"streetName\": \"" + sName + "\",\n"
                + "  \"streetNumber\": \"" + sNum + "\",\n"
                + "  \"city\": \"" + city + "\",\n"
                + "  \"zipCodeId\": 0\n"
                + "}";

        Log.e("Hej", params);
        String output = "";
        String returnValue = "";
        try {
            URL url = new URL(myUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(params.getBytes());
            outputStream.flush();

            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                return null;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while ((output = bufferedReader.readLine()) != null) {
                returnValue = output;
            }

            httpURLConnection.disconnect();

        } catch (MalformedURLException Mue) {
            Mue.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

}
