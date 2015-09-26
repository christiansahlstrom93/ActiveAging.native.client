package com.web.activeagingnativeclient;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.web.activeagingnativeclient.Constants.PublicConstants;
import com.web.activeagingnativeclient.MainInitializor.MyPagerAdapter;
import com.web.activeagingnativeclient.Resources.ResourcesHelper;
import com.web.activeagingnativeclient.SharedPreferenceHelper.SharedPreferenceHandler;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class Splash extends AppCompatActivity implements MaterialTabListener {

    private static Splash instance;

    public ViewPager pager;

    private ResourcesHelper resourcesHelper = new ResourcesHelper();
    private TextView actionBarText;
    private ProgressBar progressBar;
    private MyPagerAdapter pagerAdapter;
    MaterialTabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (SharedPreferenceHandler.getPublicLibValue(this,"username") == null) {
           //TODO starta LOG IN sidan
        }

        instance = getInstance();
        try {
            initTabs();
        } catch (Exception e) {
            Log.e(PublicConstants.TAG,"ERROR I INITABS " + e);
        }
    }

    public Splash getInstance() {
        if (instance == null) {
            instance = new Splash();
        }
        return instance;
    }

    private void initTabs() {

        tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), getApplicationContext());
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
            bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(21, 161, 100))); //TODO ändra färg

            LayoutInflater inflator = LayoutInflater.from(this);
            View v = inflator.inflate(R.layout.titleview, null);
            setActionBarText((TextView) v.findViewById(R.id.title));
            getInstance().setActionBarText(getActionBarText());
            getActionBarText().setText(SharedPreferenceHandler.getPublicLibValue(this, "username"));
            progressBar = (ProgressBar) v.findViewById(R.id.tabProgressBar);
            progressBar.setVisibility(View.INVISIBLE); //TODO ta bort
            bar.setCustomView(v);
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

    public TextView getActionBarText() {
        return actionBarText;
    }

    public void setActionBarText(TextView actionBarText) {
        this.actionBarText = actionBarText;
    }
}
