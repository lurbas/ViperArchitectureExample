package com.lucasurbas.search.util;

import android.content.Context;

/**
 * Created by Lucas on 29/08/15.
 */
public class Util {

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
