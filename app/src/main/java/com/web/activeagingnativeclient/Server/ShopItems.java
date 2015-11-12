package com.web.activeagingnativeclient.Server;

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

    private List<Integer> manufacturerID = new ArrayList<>();

    public void getManufactures() throws JSONException {
        setProductId(getResponseBody("https://activeageing.se/resources/manufacturers"),manufacturerID);
    }

    public void getProducts() throws JSONException {
        for (int i = 0; i < manufacturerID.size(); i++) {
            String url = "https://activeageing.se/resources/manufacturers/"+manufacturerID.get(i)+"/products";
            setProductCredentials(getResponseBody(url),getTitle(),getDescription(),getPrice(),getItemID());
        }
    }


    public void getImage() throws JSONException {

        for (int i = 0; i < manufacturerID.size(); i++) {
            for (int j = 0; j < getItemID().size(); j ++) {
                String url = "https://activeageing.se/resources/manufacturers/" + manufacturerID.get(i)+"/products/"+getItemID().get(j)+
                        "/media ";
                setProductImageURL(getResponseBody(url),getImageUrl());
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
