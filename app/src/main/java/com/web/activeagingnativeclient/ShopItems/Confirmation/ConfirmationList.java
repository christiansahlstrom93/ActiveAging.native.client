package com.web.activeagingnativeclient.ShopItems.Confirmation;

import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import com.web.activeagingnativeclient.Adapters.CustomShopAdapter;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.ShopItems.ShopHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-29.
 */
public class ConfirmationList extends ConfirmationActivity {
    public static CustomShopAdapter customShopAdapter;
    private List<ShopHelper> shopHelperList = new ArrayList<>();
    private ConfirmationActivity activity;

    public void addItem(String title, String desc, float price, String imageUrl, final ConfirmationActivity activity, ListView listView) {
        this.activity = activity;
        setCustomShopAdapter(new CustomShopAdapter(activity, shopHelperList, PublicConstants.ADAPTER_TYPE_CONF,this));
        listView.setAdapter(getCustomShopAdapter());
        ShopHelper shopHelper = new ShopHelper();
        shopHelper.setDescription(desc);
        shopHelper.setTitle(title);
        shopHelper.setImageURL(imageUrl);
        shopHelper.setPrice(price);
        shopHelperList.add(shopHelper);
    }

    public void notifyActionBarSetUp() {
        try {
            activity.styleActionBar(true);
        } catch (Exception e) {
            Toast.makeText(activity,"Fan " + e, Toast.LENGTH_LONG).show();
        }
    }

    public CustomShopAdapter getCustomShopAdapter() {
        return customShopAdapter;
    }

    public void setCustomShopAdapter(CustomShopAdapter customShopAdapter) {
        this.customShopAdapter = customShopAdapter;
    }
}
