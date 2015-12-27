package com.web.activeagingnativeclient.Server;

import android.content.Context;
import android.util.Log;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-11-13.
 */
public class HistoryHandler extends ServerHandler {

    private List<String> title = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();

    private List<Float> price = new ArrayList<>();
    private List<Integer> itemID = new ArrayList<>();
    private List<Integer> manufacturerID = new ArrayList<>();

    private List<Integer> orderID = new ArrayList<>();

    public void getOrders(Context context) throws JSONException {
        int id = SharedPreferenceHandler.getPublicLibValue(context, PublicConstants.ACCOUNT_KEY, 17);
        setProductId(getResponseBody("https://activeageing.se/resources/accounts/" + id + "/orders"), orderID);
    }

    public void getOrderItems(Context c) throws JSONException {
        int id = SharedPreferenceHandler.getPublicLibValue(c, PublicConstants.ACCOUNT_KEY, 17);

        for (int i = 0; i < orderID.size(); i++) {

            String response = getResponseBody("https://activeageing.se/resources/accounts/" + id + "/orders/" + orderID.get(i) + "/items");

            if (response.length() > 20) {
                setProductCredentialsFromHistory(response, getTitle(), getDescription(), getPrice(), getItemID(), manufacturerID,getImageUrl(),c);
            }
        }
    }

    public List<String> getTitle() {
        return title;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public List<Float> getPrice() {
        return price;
    }

    public List<Integer> getItemID() {
        return itemID;
    }
}
