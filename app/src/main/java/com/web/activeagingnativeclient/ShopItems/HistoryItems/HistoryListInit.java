package com.web.activeagingnativeclient.ShopItems.HistoryItems;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.web.activeagingnativeclient.Adapters.CustomShopAdapter;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.Fragments.HistoryView;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;
import com.web.activeagingnativeclient.ShopItems.ShopHelper;
import com.web.activeagingnativeclient.Splash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-11-13.
 */
public class HistoryListInit {

    private CustomShopAdapter customShopAdapter;
    private List<ShopHelper> shopHelperList = new ArrayList<>();
    private Activity activity;

    public void addItem(String title, String desc, float price, String imageUrl, final Activity activity, ListView listView) {

        this.activity = activity;
        customShopAdapter = new CustomShopAdapter(activity, shopHelperList, PublicConstants.ADAPTER_TYPE_SHOP, null);
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
                try {
                    HistoryView inst = HistoryView.getInstance();
                    clickHandler(inst.getItemID().get(position), inst.getPrice().get(position),
                            inst.getImageUrl().get(position),inst.getTitle().get(position));
                } catch (Exception e) {
                    Toast.makeText(activity, "Någonting gick fel.. " + e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void clickHandler(float itemID, float price, String imageUrl, String title) {
        ShopBagHelper.getInstance().addItems(itemID, price, imageUrl, title);
        if (ShopBagHelper.getInstance().getItemID().size() >= 1) {
            Splash.getInstance().getChartCounter().setVisibility(View.VISIBLE);
            Splash.getInstance().getChartCounter().setText(""+ShopBagHelper.getInstance().getItemID().size());
        } else {
            Splash.getInstance().getChartCounter().setVisibility(View.INVISIBLE);
        }
    }

}
