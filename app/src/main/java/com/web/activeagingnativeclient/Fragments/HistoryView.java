package com.web.activeagingnativeclient.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.web.activeagingnativeclient.AppController;
import com.web.activeagingnativeclient.CommonHelpers.TaskHelper;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.ShopItems.HistoryItems.HistoryListInit;
import com.web.activeagingnativeclient.ShopItems.ShopListInitializor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class HistoryView extends Fragment {

    private View v;
    private static HistoryView instance;
    private HistoryListInit historyListInit;

    private List<String> title = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();
    private List<Float> price = new ArrayList<>();
    private List<Integer> itemID = new ArrayList<>();
    private TaskHelper taskHelper;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getV() == null) {
            setV(inflater.inflate(R.layout.history_view, container, false));
        }
        return getV();
    }

    public void setListShop(final String title, final String desc, final float price, final String imageUrl, final int itemId) {

        if (historyListInit == null) {
            historyListInit = new HistoryListInit();
        }

        if (listView == null) {
            listView = (ListView) getV().findViewById(R.id.listView);
        }

        ImageRequest request = new ImageRequest(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(final Bitmap bitmap) {
                        try {
                            getItemID().add(itemId);
                            getTitle().add(title);
                            getDescription().add(desc);
                            getImageUrl().add(imageUrl);
                            getPrice().add(price);
                            historyListInit.addItem(title, desc, price, imageUrl, getActivity(), listView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance().addToRequestQueue(request);
    }

    public static HistoryView getInstance() {
        if (instance == null) {
            instance = new HistoryView();
        }
        return instance;
    }

    public void startTask(Context c) {
        if (taskHelper == null) {
            taskHelper = new TaskHelper();
            taskHelper.historyTask(c);
        }
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Float> getPrice() {
        return price;
    }

    public void setPrice(List<Float> price) {
        this.price = price;
    }

    public List<Integer> getItemID() {
        return itemID;
    }

    public void setItemID(List<Integer> itemID) {
        this.itemID = itemID;
    }

}
