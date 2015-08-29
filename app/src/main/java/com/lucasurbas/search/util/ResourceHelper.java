package com.lucasurbas.search.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.lucasurbas.search.R;

/**
 * Created by Lucas on 29/08/15.
 */
public class ResourceHelper {

    /**
     * Get a color value from a theme attribute.
     *
     * @param context      used for getting the color.
     * @param attribute    theme attribute.
     * @param defaultColor default to use.
     * @return color value
     */
    public static int getThemeColor(Context context, int attribute, int defaultColor) {
        int themeColor = 0;
        String packageName = context.getPackageName();
        try {
            Context packageContext = context.createPackageContext(packageName, 0);
            packageContext.setTheme(R.style.AppTheme);
            Resources.Theme theme = packageContext.getTheme();
            TypedArray ta = theme.obtainStyledAttributes(new int[]{attribute});
            themeColor = ta.getColor(0, defaultColor);
            ta.recycle();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return themeColor;
    }
}
