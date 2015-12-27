package com.web.activeagingnativeclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.web.activeagingnativeclient.Adapters.PagerAdapter;
import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.Login.LoginActivity;
import com.web.activeagingnativeclient.Resources.ResourcesHelper;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;
import com.web.activeagingnativeclient.ShopItems.Confirmation.ConfirmationActivity;
import com.web.activeagingnativeclient.ShopItems.ShopHandler.ShopBagHelper;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Christian on 2015-09-26.
 */
public class Splash extends AppCompatActivity implements MaterialTabListener {

    private static Splash instance;

    public static ViewPager pager;

    private ResourcesHelper resourcesHelper = new ResourcesHelper();
    private TextView actionBarText;
    private TextView chartCounter;
    public ImageView shopChart;
    public static ProgressBar progressBar;
    private PagerAdapter pagerAdapter;
    MaterialTabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (SharedPreferenceHandler.getPublicLibValue(this, PublicConstants.USER) == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        instance = getInstance();
        try {
            initTabs();
        } catch (Exception e) {
            Log.e(PublicConstants.TAG, "ERROR I INITABS " + e);
        }
    }

    public static Splash getInstance() {
        if (instance == null) {
            instance = new Splash();
        }
        return instance;
    }

    private void initTabs() {

        tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getApplicationContext());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(pagerAdapter.getIcon(i))
                            .setTabListener(this)
            );
        }


        android.support.v7.app.ActionBar bar = getSupportActionBar();

        if (bar != null) {
            bar.setDisplayShowCustomEnabled(true);
            bar.setDisplayShowTitleEnabled(false);
            bar.setIcon(getResources().getDrawable(resourcesHelper.getImagedrawablesForIndex(0)));
            bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(21, 161, 100)));

            LayoutInflater inflator = LayoutInflater.from(this);
            View v = inflator.inflate(R.layout.titleview, null);
            setActionBarText((TextView) v.findViewById(R.id.title));
            getInstance().setChartCounter((TextView) v.findViewById(R.id.chartCounter));
            getInstance().setActionBarText(getActionBarText());
            getActionBarText().setText(SharedPreferenceHandler.getPublicLibValue(this, PublicConstants.USER));
            progressBar = (ProgressBar) v.findViewById(R.id.tabProgressBar);
            progressBar.setVisibility(View.INVISIBLE);
            shopChart = (ImageView) v.findViewById(R.id.imageView);
            shopChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmationStage();
                }
            });

            ImageView health = (ImageView) v.findViewById(R.id.imageView2);
            health.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createBuilder();
                }
            });

            if (ShopBagHelper.getInstance().getItemID().size() < 1) {
                getInstance().getChartCounter().setVisibility(View.INVISIBLE);
            }
            bar.setCustomView(v);
        }
    }

    private void confirmationStage() {
        if (ShopBagHelper.getInstance().getItemID().size() < 1) {
            Toast.makeText(getApplicationContext(), "Du har inte valt något att beställa", Toast.LENGTH_LONG).show();
            pager.setCurrentItem(1);
        } else {
            startActivity(new Intent(this, ConfirmationActivity.class));
        }
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ShopBagHelper.getInstance().getItemID().size() >= 1) {
            Splash.getInstance().getChartCounter().setVisibility(View.VISIBLE);
            Splash.getInstance().getChartCounter().setText("" + ShopBagHelper.getInstance().getItemID().size());
        } else {
            Splash.getInstance().getChartCounter().setVisibility(View.INVISIBLE);
        }
    }

    public TextView getActionBarText() {
        return actionBarText;
    }

    public void setActionBarText(TextView actionBarText) {
        this.actionBarText = actionBarText;
    }

    public TextView getChartCounter() {
        return chartCounter;
    }

    public void setChartCounter(TextView chartCounter) {
        this.chartCounter = chartCounter;
    }

    private void createBuilder() {

        final String[] items = {"Nötter","Fisk","Curry","Laktos","Svamp","Mjölk"};

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.allergic_builder, null);
        LinearLayout linearLayout = (LinearLayout) dialogView.findViewById(R.id.builderview);

        for (int i = 0; i < items.length; i++) {
            final int pos = i;
            final CheckBox check = new CheckBox(this);
            check.setText(items[i]);
            check.setChecked(SharedPreferenceHandler.getPublicLibValue(Splash.this,items[pos],PublicConstants.PUBLIC_ALERGIC_KEY) != null);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (check.isChecked()) {
                        SharedPreferenceHandler.setValueToGlobalLib(Splash.this, items[pos], items[pos], PublicConstants.PUBLIC_ALERGIC_KEY);
                        SharedPreferenceHandler.getAllAllergies(Splash.this);
                    } else {
                        SharedPreferenceHandler.removeValue(Splash.this, items[pos]);
                    }

                }
            });

            linearLayout.addView(check);
        }

        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
