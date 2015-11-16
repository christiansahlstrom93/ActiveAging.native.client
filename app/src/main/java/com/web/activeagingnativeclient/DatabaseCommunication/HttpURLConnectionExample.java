package com.web.activeagingnativeclient.DatabaseCommunication;

/**
 * Created by Admir on 2015-11-13.
 */
import android.content.Context;

import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {
    private final String USER_AGENT = "Mozilla/5.0";

    String type;
    String city;
    String created;
    String email;
    String firstName;
    String id;
    String lastName;
    String phoneNumber;
    String role;
    String streetName;
    String streetNumber;
    String updated;
    String userName;

    String incomingID;

    String inOldPassword;
    String inNewPassword;
    String userID;

    String inFirstName;
    String inLastName;
    String inStreetName;
    String inStreetNumber;
    String inZipCode;
    String inCity;
    String inEmail;
    String inPhone;

    public void sendGet(String incomingID) throws Exception {
        this.incomingID = incomingID;
        String url = "https://activeageing.se/resources/accounts/" + incomingID;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject object = new JSONObject(response.toString());

        type = object.getString("type");
        city = object.getString("city");
        created = object.getString("created");
        email = object.getString("email");
        firstName = object.getString("firstName");
        id = object.getString("id");
        lastName = object.getString("lastName");
        phoneNumber = object.getString("phoneNumber");
        role = object.getString("role");
        streetName = object.getString("streetName");
        streetNumber = object.getString("streetNumber");
        updated = object.getString("updated");
        userName = object.getString("userName");

        //System.out.println(type + city + created + email + firstName + id + lastName + phoneNumber + role + streetName + streetNumber + updated + userName);
        //System.out.println(response.toString());
    }

    public void sendPostPassword(String inOldPassword, String inNewPassword, String userID, Context c) throws Exception {
        this.inOldPassword = inOldPassword;
        this.inNewPassword = inNewPassword;
        this.userID = userID;

        //String url = "https://activeageing.se/resources/accounts/" + userID + "/password";
        String url = "https://activeageing.se/resources/accounts/1/password";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("oldPassword", inOldPassword);
        jsonParam.put("newPassword", inNewPassword);

        String str = jsonParam.toString();
        byte[] data = str.getBytes("UTF-8");
        wr.write(data);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'PUT' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            SharedPreferenceHandler.setValueToGlobalLib(c, "", inNewPassword);
        }
        in.close();

        System.out.println(response.toString());
    }

    public void sendPostUserData(String inFirstName, String inLastName, String inStreetName, String inStreetNumber, String inZipCode, String inCity, String inEmail, String inPhone) throws Exception {
        this.inFirstName = inFirstName;
        this.inLastName = inLastName;
        this.inStreetName = inStreetName;
        this.inStreetNumber = inStreetNumber;
        this.inZipCode = inZipCode;
        this.inCity = inCity;
        this.inEmail = inEmail;
        this.inPhone = inPhone;

        //String url = "https://activeageing.se/resources/accounts/" + userID + "/password";
        String url = "https://activeageing.se/resources/accounts/1";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("firstName", inFirstName);
        jsonParam.put("lastName", inLastName);
        jsonParam.put("email", inEmail);
        jsonParam.put("phoneNumber", inPhone);
        jsonParam.put("streetName", inStreetName);
        jsonParam.put("streetNumber", inStreetNumber);
        jsonParam.put("city", inCity);
        jsonParam.put("zipCodeId", inZipCode);

        String str = jsonParam.toString();
        byte[] data = str.getBytes("UTF-8");
        wr.write(data);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'PUT' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }

    public String typeMethod() {
        return type;
    }

    public String cityMethod() {
        return city;
    }

    public String createdMethod() {
        return created;
    }

    public String emailMethod() {
        return email;
    }

    public String firstNameMethod() {
        return firstName;
    }

    public String idMethod() {
        return id;
    }

    public String lastNameMethod() {
        return lastName;
    }

    public String phoneNumberMethod() {
        return phoneNumber;
    }

    public String roleMethod() {
        return role;
    }

    public String streetNameMethod() {
        return streetName;
    }

    public String streetNumberMethod() {
        return streetNumber;
    }

    public String updatedMethod() {
        return updated;
    }

    public String usernameMethod() {
        return userName;
    }

}
