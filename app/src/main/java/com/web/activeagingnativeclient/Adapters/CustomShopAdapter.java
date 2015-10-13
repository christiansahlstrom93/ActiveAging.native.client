package com.web.activeagingnativeclient.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.web.activeagingnativeclient.CommonHelpers.URLConvertion;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.ShopItems.ShopHelper;

import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class CustomShopAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<ShopHelper> shopHelperList;

    public CustomShopAdapter(Activity activity,List<ShopHelper> shopHelperList) {
        this.activity = activity;
        this.shopHelperList = shopHelperList;
    }

    @Override
    public int getCount() {
        return this.shopHelperList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.shopHelperList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_row,null);
        }

        final ShopHelper shopHelper = this.shopHelperList.get(position);
        TextView description, title, price;
        ImageView imageView, infoIcon;

        description = (TextView) convertView.findViewById(R.id.description);
        title= (TextView) convertView.findViewById(R.id.title);
        price = (TextView) convertView.findViewById(R.id.price);
        imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
        infoIcon = (ImageView) convertView.findViewById(R.id.infoImage);

        description.setText(shopHelper.getDescription());
        title.setText(shopHelper.getTitle());
        price.setText("Pris " + shopHelper.getPrice() + " SEK");
        try {
            new URLConvertion().handleBitmapUrl(imageView,shopHelper.getImageURL());
        } catch (Exception e) {
            Log.e(PublicConstants.TAG,"Error i CSA " + e);
        }

        infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Öppna en ny description ruta
                Toast.makeText(activity,shopHelper.getDescription(),Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }

}
