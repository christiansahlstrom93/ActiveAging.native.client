package com.web.activeagingnativeclient.CommonHelpers;

import android.content.Context;
import android.os.AsyncTask;

import com.web.activeagingnativeclient.Fragments.ShopView;
import com.web.activeagingnativeclient.Server.ShopItems;

/**
 * Created by Christian on 2015-10-02.
 */
public class TaskHelper {

    public void shopTask() {
        final ShopItems shopItems = new ShopItems();
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                while (!shopItems.connect());

                //TODO byta query
                shopItems.fetchShopInformation("ge mig mad");

                ShopView.getInstance().setDescription(shopItems.getDescription());
                ShopView.getInstance().setImageUrl(shopItems.getImageUrl());
                ShopView.getInstance().setPrice(shopItems.getPrice());
                ShopView.getInstance().setTitle(shopItems.getTitle());
                ShopView.getInstance().setItemID(shopItems.getItemID());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                for (int i = 0; i < ShopView.getInstance().getDescription().size(); i++) {
                    ShopView.getInstance().setListShop(ShopView.getInstance().getTitle().get(i),
                            ShopView.getInstance().getDescription().get(i),ShopView.getInstance().getPrice().get(i),
                            ShopView.getInstance().getImageUrl().get(i));
                }
            }
        }.execute();

    }

}
