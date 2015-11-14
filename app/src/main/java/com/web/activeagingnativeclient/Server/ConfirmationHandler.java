package com.web.activeagingnativeclient.Server;

import android.util.Log;

import com.web.activeagingnativeclient.Constants.PublicConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Christian on 2015-11-12.
 */
public class ConfirmationHandler extends ServerHandler {

    public int postOrder(int id) throws IOException, JSONException {
        Log.e(PublicConstants.TAG, "PostOrder");
        String response = addOrder("https://activeageing.se/resources/accounts/" + id + "/orders");
        if (response != null) {
            return orderId(response);
        }

        return PublicConstants.ORDER_FAIL;
    }

    public int addItemToOrder(int orderID, int userID, float productID) throws IOException, JSONException {
        int prod = Math.round(productID);
        Log.e(PublicConstants.TAG, "Add item " + prod + " " + orderID);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String params = "{\"delivered\":\""+dateFormat.format(date)+"\",\"productId\": "+prod+"}";

        String response = addOrder("https://activeageing.se/resources/accounts/" + userID + "/orders/" + orderID + "/items",params);
        if (response != null) {
            return orderId(response);
        }

        return PublicConstants.ORDER_FAIL;
    }

    private int orderId(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getInt("id");
    }
}
