package com.web.activeagingnativeclient.ShopItems.ShopHandler;

import com.web.activeagingnativeclient.Constants.PublicConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-13.
 */
public class ShopBagHelper extends ShopBag {

    public static ShopBagHelper shopHelper;

    public void addItems(float itemID, float price, String imageUrl, String title) {
        getPrice().add(price);
        getItemID().add(itemID);
        getImageUrl().add(imageUrl);
        getTitle().add(title);
    }

    public static ShopBagHelper getInstance() {
        if (shopHelper == null) {
            shopHelper = new ShopBagHelper();
        }
        return shopHelper;
    }

    @Override
    public void clearLists() {
        getPrice().clear();
        getItemID().clear();
        getImageUrl().clear();
        getTitle().clear();
    }

    @Override
    public void clearListForIndex(int position) {

        List<String> title = new ArrayList<>();
        List<String> imageUrl = new ArrayList<>();
        List<Float> price = new ArrayList<>();
        List<Float> itemID = new ArrayList<>();

        for (int i = 0; i < getPrice().size(); i++) {
            if (i != position) {
                title.add(getTitle().get(i));
                price.add(getPrice().get(i));
                itemID.add(getItemID().get(i));
                imageUrl.add(getImageUrl().get(i));
            }
        }

        getPrice().clear();
        getItemID().clear();
        getImageUrl().clear();
        getTitle().clear();

        setItemID(itemID);
        setPrice(price);
        setImageUrl(imageUrl);
        setTitle(title);
    }
}
