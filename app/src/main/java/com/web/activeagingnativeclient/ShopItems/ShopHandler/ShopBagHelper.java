package com.web.activeagingnativeclient.ShopItems.ShopHandler;

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

}
