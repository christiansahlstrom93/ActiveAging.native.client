package com.web.activeagingnativeclient.ShopItems.ShopHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-13.
 */
public abstract class ShopBag {

    private List<String> title = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();

    private List<Float> price = new ArrayList<>();
    private List<Float> itemID = new ArrayList<>();

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
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

    public List<Float> getItemID() {
        return itemID;
    }

    public abstract void clearLists();

    public abstract void clearListForIndex(int position);

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(List<Float> price) {
        this.price = price;
    }

    public void setItemID(List<Float> itemID) {
        this.itemID = itemID;
    }
}
