package com.web.activeagingnativeclient.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.web.activeagingnativeclient.Fragments.HistoryView;
import com.web.activeagingnativeclient.Fragments.SettingsView;
import com.web.activeagingnativeclient.Fragments.ShopView;
import com.web.activeagingnativeclient.Fragments.ProfileView;
import com.web.activeagingnativeclient.Resources.ResourcesHelper;

/**
 * Created by Christian on 2015-09-26.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private ResourcesHelper resourcesHelper = new ResourcesHelper();

    static int[] icons;
    Context c;

    public PagerAdapter(FragmentManager fm, Context v) {
        super(fm);
        this.c = v;
        icons = new int[]{resourcesHelper.getImagedrawablesForIndex(0), resourcesHelper.getImagedrawablesForIndex(1), resourcesHelper.getImagedrawablesForIndex(3), resourcesHelper.getImagedrawablesForIndex(2)};
    }

    @Override
    public Fragment getItem(int position) {

        try {
            //TODO här ska fyra olika fragments in
            switch (position) {
                case 0:
                    return new ProfileView();
                case 1:
                    ShopView.getInstance().startTask();
                    return ShopView.getInstance();
                case 2:
                    return new HistoryView();
                case 3:
                    return new SettingsView();

                default:
                    break;
            }
        } catch (Exception e) {

        }


        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public Drawable getIcon(int position) {
        Drawable drawable = c.getResources().getDrawable(icons[position]);
        return drawable;
    }
}
