package com.lucasurbas.search.widget;

import android.content.Context;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasurbas.search.App;
import com.lucasurbas.search.DetailActivity;
import com.lucasurbas.search.R;
import com.lucasurbas.search.model.SearchItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        App.getObjectGraph().inject(this);
    }

    public static int getLayoutResId() {
        return R.layout.row_user;
    }

    public void presentSearchItem(final Context context, final SearchItem searchItem) {

        if (searchItem.isFavourite()) {
            ivFavourite.setVisibility(View.VISIBLE);
            DrawableCompat.setTint(ivFavourite.getDrawable(), context.getResources().getColor(R.color.toolbar_icon));
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
        picasso.load(searchItem.getImageUrl())
                .fit()
                .placeholder(R.drawable.placeholder_avatar)
                .into(ivAvatar);
        tvLogin.setText(searchItem.getTitle());

        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailActivity.getStartIntent(context, searchItem));
            }
        });
    }
}
