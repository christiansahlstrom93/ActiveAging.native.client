package com.web.activeagingnativeclient.ShopItems.Confirmation;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.web.activeagingnativeclient.CommonHelpers.TaskHelper;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.R;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2015-10-22.
 */
public class ConfirmationActivity extends AppCompatActivity {

    Animation alpha, flash;
    private List<Integer> orderID = new ArrayList<>();
    private android.support.v7.app.ActionBar bar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        alpha = AnimationUtils.loadAnimation(this, R.anim.alphastyle);
        flash = AnimationUtils.loadAnimation(this, R.anim.sumflash);
        setBar(getSupportActionBar());
        fillList();
        styleActionBar(false);
    }

    private void fillList() {
        ConfirmationList confirmationList = new ConfirmationList();
        ListView listView = (ListView) findViewById(R.id.listView2);
        for (int i = 0; i < ShopBagHelper.getInstance().getItemID().size(); i++) {
            confirmationList.addItem(ShopBagHelper.getInstance().getTitle().get(i),
                    "", ShopBagHelper.getInstance().getPrice().get(i),
                    ShopBagHelper.getInstance().getImageUrl().get(i), this, listView);
        }
    }

    public void processOrder(View v) {
        progressDialog = new ProgressDialog(ConfirmationActivity.this);
        progressDialog.setMessage("Din beställning behandlas");
        progressDialog.setCancelable(false);
        progressDialog.show();
        v.startAnimation(alpha);

        int id = SharedPreferenceHandler.getPublicLibValue(this,PublicConstants.ACCOUNT_KEY, 17);

        TaskHelper taskHelper = new TaskHelper();
        taskHelper.createOrder(id, getOrderID(), ShopBagHelper.getInstance().getItemID(),this);

    }


    public void styleActionBar(boolean b) {

        if (getBar() != null) {

            bar.setDisplayShowCustomEnabled(true);
            bar.setDisplayShowTitleEnabled(false);
            bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(21, 161, 100)));

            LayoutInflater inflator = LayoutInflater.from(this);
            View v = inflator.inflate(R.layout.conf_actionbar, null);
            TextView actionBartext = (TextView) v.findViewById(R.id.title);
            actionBartext.setText("Bekräfta din order");
            final TextView sum = (TextView) v.findViewById(R.id.sum);
            sum.setText("" + getSum() + " SEK");
            bar.setCustomView(v);

            if (b && getSum() > 0) {
                sum.setTextColor(Color.RED);
                sum.setText("" + getSum() + "");
                sum.startAnimation(flash);

                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        sum.setTextColor(Color.WHITE);
                        sum.setText("" + getSum() + " SEK");
                    }
                }.start();
            } else if (getSum() <= 0) {
                finish();
            }
        }
    }

    private double getSum() {
        double sum = 0;
        for (int i = 0; i < ShopBagHelper.getInstance().getPrice().size(); i++) {
            sum += ShopBagHelper.getInstance().getPrice().get(i);
        }
        return sum;
    }

    public android.support.v7.app.ActionBar getBar() {
        return bar;
    }

    public void setBar(android.support.v7.app.ActionBar bar) {
        this.bar = bar;
    }

    public List<Integer> getOrderID() {
        return orderID;
    }

    public void setOrderID(List<Integer> orderID) {
        this.orderID = orderID;
    }
}
