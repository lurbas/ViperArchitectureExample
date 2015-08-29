package com.lucasurbas.search.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Lucas on 29/08/15.
 */
public class GridSpacingDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        int spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, view.getResources().getDisplayMetrics());
        int halfSpacing = spacing / 2;

        int childCount = parent.getChildCount();
        int childIndex = parent.getChildAdapterPosition(view);
        int spanCount = getTotalSpan(view, parent);
        int spanIndex = childIndex % spanCount;

        /* INVALID SPAN */
        if (spanCount < 1) return;

        outRect.top = halfSpacing;
        outRect.bottom = halfSpacing;
        outRect.left = halfSpacing;
        outRect.right = halfSpacing;

        if (isTopEdge(childIndex, spanCount)) {
            outRect.top = spacing;
        }
//        if (isLeftEdge(spanIndex, spanCount)) {
//            outRect.left = spacing;
//        }
//        if (isRightEdge(spanIndex, spanCount)) {
//            outRect.right = spacing;
//        }
        if (isBottomEdge(childIndex, childCount, spanCount)) {
            outRect.bottom = spacing;
        }
    }

    protected int getTotalSpan(View view, RecyclerView parent) {
        RecyclerView.LayoutManager mgr = parent.getLayoutManager();
        if (mgr instanceof GridLayoutManager) {
            return ((GridLayoutManager) mgr).getSpanCount();
        } else if (mgr instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) mgr).getSpanCount();
        }
        return -1;
    }

    protected boolean isLeftEdge(int spanIndex, int spanCount) {
        return spanIndex == 0;
    }

    protected boolean isRightEdge(int spanIndex, int spanCount) {
        return spanIndex == spanCount - 1;
    }

    protected boolean isTopEdge(int childIndex, int spanCount) {
        return childIndex < spanCount;
    }

    protected boolean isBottomEdge(int childIndex, int childCount, int spanCount) {
        return childIndex >= childCount - spanCount;
    }
}
