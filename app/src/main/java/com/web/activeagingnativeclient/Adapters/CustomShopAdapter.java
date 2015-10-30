package com.web.activeagingnativeclient.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.web.activeagingnativeclient.CommonHelpers.URLConvertion;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.ShopItems.Confirmation.ConfirmationList;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBag;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;
import com.web.activeagingnativeclient.ShopItems.ShopHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-02.
 */
public class CustomShopAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<ShopHelper> shopHelperList;
    private String type;
    private ConfirmationList confList;

    public CustomShopAdapter(Activity activity, List<ShopHelper> shopHelperList, String type, ConfirmationList confList) {
        this.activity = activity;
        this.shopHelperList = shopHelperList;
        this.type = type;
        this.confList = confList;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_row, null);
        }

        final ShopHelper shopHelper = this.shopHelperList.get(position);
        TextView description, title, price,clickText;
        ImageView imageView, infoIcon;
        ProgressBar pb;

        description = (TextView) convertView.findViewById(R.id.description);
        title = (TextView) convertView.findViewById(R.id.title);
        price = (TextView) convertView.findViewById(R.id.price);
        imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
        infoIcon = (ImageView) convertView.findViewById(R.id.infoImage);
        pb = (ProgressBar) convertView.findViewById(R.id.progressBar);
        clickText = (TextView) convertView.findViewById(R.id.clickText);
        clickText.setTextSize(13);
        description.setText(shopHelper.getDescription());
        title.setText(shopHelper.getTitle());
        price.setText("Pris " + shopHelper.getPrice() + " SEK");
        try {
            new URLConvertion().handleBitmapUrl(imageView, shopHelper.getImageURL(), pb);
        } catch (Exception e) {
            Log.e(PublicConstants.TAG, "Error i CSA " + e);
        }

        switch (type) {
            case PublicConstants.ADAPTER_TYPE_SHOP:
                clickText.setText("Klicka för info");
                infoIcon.setImageResource(R.mipmap.ic_info_outline_black_24dp);
                infoIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Öppna en ny description ruta
                        Toast.makeText(activity, shopHelper.getDescription(), Toast.LENGTH_LONG).show();
                    }
                });
                clickText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, shopHelper.getDescription(), Toast.LENGTH_LONG).show();
                    }
                });
                break;

            case PublicConstants.ADAPTER_TYPE_CONF:

                infoIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShopBagHelper.getInstance().clearListForIndex(position);
                        if (ConfirmationList.customShopAdapter != null) {
                            Toast.makeText(activity, "Pos" + position, Toast.LENGTH_SHORT).show();
                            shopHelperList = listRemoval(shopHelperList, position);
                            ConfirmationList.customShopAdapter.notifyDataSetChanged();
                        }
                        confList.notifyActionBarSetUp();
                    }
                });
                clickText.setText("Klicka för att ta bort produkten");
                clickText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(activity, shopHelper.getDescription(), Toast.LENGTH_LONG).show();
                    }
                });

                break;
        }
        return convertView;
    }

    private List<ShopHelper> listRemoval(List<ShopHelper> shopHelperList, int pos) {

        List<ShopHelper> tempList = new ArrayList<>();

        for (int i = 0; i < shopHelperList.size(); i++) {
            if (i != pos) {
                tempList.add(shopHelperList.get(i));
            }
        }
        shopHelperList.clear();
        return tempList;
    }

}
