package com.web.activeagingnativeclient.Server;

import android.content.Context;
import android.util.Log;

import com.web.activeagingnativeclient.Constants.PublicConstants;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class ShopItems extends ServerHandler {

    private List<String> title = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();

    private List<Float> price = new ArrayList<>();
    private List<Integer> itemID = new ArrayList<>();

    public void getProducts(Context c) throws JSONException {
            String url;
            url = "https://activeageing.se/resources/products";
            setProductCredentials(getResponseBody(url),getTitle(),getDescription(),getPrice(),getItemID(),getImageUrl(),c);
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
