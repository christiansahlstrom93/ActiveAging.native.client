package com.web.activeagingnativeclient.ShopItems;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.web.activeagingnativeclient.Adapters.CustomShopAdapter;
import com.web.activeagingnativeclient.Fragments.ShopView;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class ShopListInitializor {

    private CustomShopAdapter customShopAdapter;
    private List<ShopHelper> shopHelperList = new ArrayList<>();

    public void addItem(String title, String desc, float price, String imageUrl, final Activity activity, ListView listView) {

        customShopAdapter = new CustomShopAdapter(activity, shopHelperList);
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
                    ShopView inst = ShopView.getInstance();
                    clickHandler(inst.getItemID().get(position), inst.getPrice().get(position),
                            inst.getImageUrl().get(position),inst.getTitle().get(position));
                } catch (Exception e) {
                    Toast.makeText(activity, "Någonting gick fel.. ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void clickHandler(float itemID, float price, String imageUrl, String title) {
        Toast.makeText(ShopView.getInstance().getContext(), "Item = " + itemID, Toast.LENGTH_SHORT).show();
        ShopBagHelper.getInstance().addItems(itemID,price,imageUrl,title);
    }
}
