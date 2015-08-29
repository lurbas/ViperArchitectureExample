package com.lucasurbas.search.widget;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

import com.lucasurbas.search.util.Util;

/**
 * Created by Lucas on 29/08/15.
 */
public class RecyclerViewHelper {

    public static void setup(Context context, final RecyclerView recyclerView){
        final GridLayoutManager manager = new GridLayoutManager(context, 2);
        final int columnWidth = (int) Util.pxFromDp(context, 150);
        int padding = (int) Util.pxFromDp(context, 2);
        recyclerView.setPadding(padding, 0, padding, 0);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingDecoration());

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (columnWidth > 0) {
                    int spanCount = Math.max(1, (recyclerView.getMeasuredWidth() - recyclerView.getPaddingLeft() - recyclerView.getPaddingRight()) / columnWidth);
                    manager.setSpanCount(spanCount);
                }
            }
        });
    }
}
