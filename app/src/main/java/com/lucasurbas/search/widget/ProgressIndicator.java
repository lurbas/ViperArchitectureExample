package com.lucasurbas.search.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

import com.lucasurbas.search.R;
import com.lucasurbas.search.util.Util;

/**
 * Created by Lucas on 29/08/15.
 */
public class ProgressIndicator extends FrameLayout {

    public ProgressIndicator(Context context) {
        super(context);
        inflateChild(context);
        init();
    }

    public ProgressIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateChild(context);
        init();
    }

    private void inflateChild(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_progress_indicator, this, true);
    }

    private void init() {

        ViewCompat.setElevation(this, Util.pxFromDp(getContext(), 8));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void getOutline(View view, Outline outline) {
                    // Or read size directly from the view's width/height
                    int w = ViewCompat.isLaidOut(ProgressIndicator.this) ? getWidth() : getMeasuredWidth();
                    int h = ViewCompat.isLaidOut(ProgressIndicator.this) ? getHeight() : getMeasuredHeight();
                    outline.setOval(0, 0, w, h);
                }
            };
            setBackgroundColor(Color.WHITE);
            setOutlineProvider(viewOutlineProvider);
            setClipToOutline(true);
        } else {
            setBackgroundResource(R.drawable.background_progress_bar);
        }
    }
}
