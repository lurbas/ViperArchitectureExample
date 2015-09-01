package com.lucasurbas.search.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasurbas.search.App;
import com.lucasurbas.search.R;
import com.lucasurbas.search.event.OpenDetailScreenEvent;
import com.lucasurbas.search.model.SearchItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Lucas on 30/08/15.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    @Inject
    Picasso picasso;

    @Bind(R.id.item_container)
    View itemContainer;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.iv_favourite)
    ImageView ivFavourite;
    @Bind(R.id.tv_best_result)
    TextView tvBestResult;

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        App.getObjectGraph().inject(this);
    }

    public static int getLayoutResId() {
        return R.layout.row_user;
    }

    public void presentSearchItem(final Context context, final SearchItem searchItem, boolean bestResult) {

        if (searchItem.isFavourite()) {
            ivFavourite.setVisibility(View.VISIBLE);

            final Drawable originalDrawable = ivFavourite.getDrawable();
            final Drawable wrappedDrawable = DrawableCompat.wrap(originalDrawable);

            int color = context.getResources().getColor(R.color.toolbar_icon);
            DrawableCompat.setTint(wrappedDrawable, color);
            ivFavourite.setImageDrawable(wrappedDrawable);
        } else {
            ivFavourite.setVisibility(View.GONE);
        }
        if (searchItem.isVisited()) {
            ivAvatar.setAlpha(0.33f);
            tvLogin.setAlpha(0.33f);
        } else {
            ivAvatar.setAlpha(1f);
            tvLogin.setAlpha(1f);
        }
        tvBestResult.setVisibility(bestResult ? View.VISIBLE : View.GONE);

        picasso.load(searchItem.getImageUrl())
                .fit()
                .placeholder(R.drawable.placeholder_avatar)
                .into(ivAvatar);
        tvLogin.setText(searchItem.getTitle());

        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new OpenDetailScreenEvent(searchItem));
            }
        });
    }
}
