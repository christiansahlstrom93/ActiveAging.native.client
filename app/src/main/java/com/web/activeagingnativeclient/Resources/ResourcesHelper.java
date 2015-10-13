package com.web.activeagingnativeclient.Resources;

import com.web.activeagingnativeclient.R;

/**
 * Created by Christian on 2015-09-26.
 */
public class ResourcesHelper {
    //TODO lägg in andra ikoner för tabbar och andra ändamål
    private int[] imagedrawables = {R.mipmap.ic_perm_identity_white_24dp,R.mipmap.ic_shopping_cart_white_24dp,
            R.mipmap.ic_build_white_24dp,R.mipmap.ic_subject_white_24dp};

    public int getImagedrawablesForIndex(int index) {
        return getImagedrawables()[index];
    }

    public int[] getImagedrawables() {
        return imagedrawables;
    }
}
