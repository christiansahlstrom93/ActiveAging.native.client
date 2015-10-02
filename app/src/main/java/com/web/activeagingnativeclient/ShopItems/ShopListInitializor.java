package com.web.activeagingnativeclient.ShopItems;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.web.activeagingnativeclient.Adapters.CustomShopAdapter;
import com.web.activeagingnativeclient.Constants.PublicConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class ShopListInitializor {

    private CustomShopAdapter customShopAdapter;
    private List<ShopHelper> shopHelperList = new ArrayList<>();

    public void addItem(String title, String desc, float price, String imageUrl, Activity activity,ListView listView) {

        customShopAdapter = new CustomShopAdapter(activity,shopHelperList);
        listView.setAdapter(customShopAdapter);
        ShopHelper shopHelper = new ShopHelper();
        shopHelper.setDescription(desc);
        shopHelper.setTitle(title);
        shopHelper.setImageURL(imageUrl);
        shopHelper.setPrice(price);
        shopHelperList.add(shopHelper);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO fixa klicken
            }
        });
    }
}
