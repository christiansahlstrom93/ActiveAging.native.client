package com.web.activeagingnativeclient.Resources;

import com.web.activeagingnativeclient.R;

/**
 * Created by Christian on 2015-09-26.
 */
public class ResourcesHelper {
    //TODO lägg in andra ikoner för tabbar och andra ändamål
    private int[] imagedrawables = {R.drawable.left_arrow,R.drawable.left_arrow,R.drawable.left_arrow,R.drawable.left_arrow};

    public int getImagedrawablesForIndex(int index) {
        return getImagedrawables()[index];
    }

    public int[] getImagedrawables() {
        return imagedrawables;
    }
}
