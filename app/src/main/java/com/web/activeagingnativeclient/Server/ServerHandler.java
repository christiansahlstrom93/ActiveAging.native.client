package com.web.activeagingnativeclient.Server;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.web.activeagingnativeclient.Constants.PublicConstants;

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
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public abstract class ServerHandler {

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

    public void setProductId(String jsonData, List<Integer> idList) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArr.length(); i++) {
            jsonObject = new JSONObject(String.valueOf(jsonArr.get(i)));
            idList.add(jsonObject.getInt("id"));
        }
    }

    public void setProductCredentials(String jsonData, List<String> title, List<String> desc, List<Float> price,
                                      List<Integer> itemID) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArr.length(); i++) {
            jsonObject = new JSONObject(String.valueOf(jsonArr.get(i)));
            itemID.add(jsonObject.getInt("id"));
            desc.add(jsonObject.getString("description"));
            title.add(jsonObject.getString("name"));
            price.add((float) jsonObject.getDouble("price"));
        }
    }

    public void setProductCredentialsFromHistory(String response, List<String> title, List<String> desc, List<Float> price, List<Integer> itemID, List<Integer> manufacturerID) throws JSONException {

        JSONArray jsonArr = new JSONArray(response);
        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArr.get(0)));
        JSONObject jsonObject1;
        jsonObject = jsonObject.getJSONObject("product");

        desc.add(jsonObject.getString("description"));
        itemID.add(jsonObject.getInt("id"));
        title.add(jsonObject.getString("name"));
        price.add((float) jsonObject.getDouble("price"));

        jsonObject1 = jsonObject.getJSONObject("manufacturer");
        manufacturerID.add(jsonObject1.getInt("id"));
    }

    public boolean setProductImageURL(String jsonData, List<String> imageURL) throws JSONException {
        JSONArray jsonArr = new JSONArray(jsonData);
        JSONObject jsonObject;
        if (jsonArr.length() > 0) {
            jsonObject = new JSONObject(String.valueOf(jsonArr.get(0)));
            if (jsonObject != null) {
                imageURL.add(jsonObject.getString("url"));
                return true;
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String addOrder(String murl) throws IOException {
        String output = "", returnValue = "";

        try {

            URL url = new URL(murl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            Log.e(PublicConstants.TAG, "Code " + conn.getResponseCode());
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
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

    public String addOrder(String murl, String params) {

        String output = "", returnValue = "";

        try {

            URL url = new URL(murl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();

            Log.e(PublicConstants.TAG, "Code " + conn.getResponseCode());
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
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
